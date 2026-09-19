drop database if exists covoshcoffe_db;
create database if not exists covoshcoffe_db;
use covoshcoffe_db;

create table usuarios (
    id int auto_increment primary key,
    nombre_completo varchar(150) not null,
    email varchar(150) unique not null,
    password_hash varchar(255) null, -- opcional para usuarios que entran por oauth
    proveedor_auth enum('LOCAL', 'GOOGLE', 'FACEBOOK', 'APPLE') default 'LOCAL',
    proveedor_id varchar(255) null, -- id único retornado por google/fb/apple para vincular la cuenta
    puntos int default 0,
    rol enum('ADMIN', 'CLIENTE', 'BARISTA') not null default 'CLIENTE',
    is_active boolean default true,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);

create table distritos(
	id int auto_increment primary key,
    detalle varchar(50) not null
);

create table locales (
    id int auto_increment primary key,
    razon_social varchar(100) not null,
    direccion varchar(255) not null,
    idDistrito int not null,
    horario varchar(100) not null,
    latitud decimal(10, 8),
    longitud decimal(11, 8),
    is_active boolean default true,
    foreign key (idDistrito) references distritos(id)
);

create table categorias (
    id int auto_increment primary key,
    nombre varchar(50) not null,
    is_active boolean default true
);

create table productos (
    id int auto_increment primary key,
    categoria_id int not null,
    nombre varchar(100) not null,
    descripcion text,
    precio_base decimal(8, 2) not null,
    imagen_url varchar(255),
    is_active boolean default true,
    is_nuevo boolean default true,
    is_frecuente boolean default false,
    foreign key (categoria_id) references categorias(id)
);

create table medidas (
    id int auto_increment primary key,
    nombre varchar(50) not null, -- ej: pequeño, mediano, grande
    volumen_ml int,
    precio_adicional decimal(8, 2) default 0.00,
    is_active boolean default true
);

create table grupos_personalizacion (
    id int auto_increment primary key,
    nombre varchar(50) not null,          -- ej: 'tipo de leche', 'crema batida', 'cafeína'
    es_obligatorio boolean default false, -- true: exige elegir | false: opcional
    max_seleccion int default 1,           -- 1: selección única (radio) | >1: múltiple (checkbox)
    is_active boolean default true
);

create table opciones_personalizacion (
    id int auto_increment primary key,
    grupo_id int not null,
    nombre varchar(50) not null,          -- ej: leche de avena, sin cafeína
    precio_adicional decimal(8, 2) default 0.00,
    foreign key (grupo_id) references grupos_personalizacion(id) on delete cascade
);

create table producto_grupos (
    producto_id int not null,
    grupo_id int not null,
    primary key (producto_id, grupo_id),
    foreign key (producto_id) references productos(id) on delete cascade,
    foreign key (grupo_id) references grupos_personalizacion(id) on delete cascade
);

create table cupones (
    id int auto_increment primary key,
    codigo varchar(50) unique not null,
    descuento decimal(8, 2) default 0.00,
    limite_usos int default null,
    usos_actuales int default 0,
    fecha_expiracion timestamp not null,
    activo boolean default true
);

create table pedidos (
    id int auto_increment primary key,
    usuario_id int not null,
    local_id int not null,
    cupon_id int null,
    metodo_entrega enum('EN_LOCAL', 'DELIVERY') not null,
    fecha_entrega datetime null,
    subtotal decimal(8, 2) not null,
    descuento decimal(8, 2) default 0.00,
    total decimal(8, 2) not null,
    items_total int not null,
    estado enum('PENDIENTE', 'EN_PREPARACION', 'LISTO', 'EN_CAMINO', 'ENTREGADO', 'CANCELADO') default 'PENDIENTE',
    created_at timestamp default current_timestamp,
    foreign key (usuario_id) references usuarios(id),
    foreign key (local_id) references locales(id),
    foreign key (cupon_id) references cupones(id)
);

create table detalle_pedidos (
    id int auto_increment primary key,
    pedido_id int not null,
    producto_id int not null,
    medida_id int not null,
    cantidad tinyint unsigned not null default 1,
    precio_unitario decimal(10, 2) not null,
    subtotal decimal(10, 2) generated always as (cantidad * precio_unitario) stored,
    foreign key (pedido_id) references pedidos(id) on delete cascade,
    foreign key (producto_id) references productos(id),
    foreign key (medida_id) references medidas(id)
);

create table detalle_personalizaciones (
    id int auto_increment primary key,
    detalle_pedido_id int not null,
    opcion_id int not null,
    foreign key (detalle_pedido_id) references detalle_pedidos(id) on delete cascade,
    foreign key (opcion_id) references opciones_personalizacion(id)
);

create table pagos (
    id int auto_increment primary key,
    pedido_id int not null,
    metodo enum('TARJETA_CREDITO', 'TARJETA_DEBITO', 'PAYPAL','TRANSFERENCIA_BANCARIA') not null,
    proveedor varchar(50), -- ej: visa, mastercard
    ultimos_4_digitos varchar(4),
    monto decimal(8, 2) not null,
    estado_pago enum('PENDIENTE', 'COMPLETADO', 'FALLIDO', 'REEMBOLSO') default 'PENDIENTE',
    pagado_en timestamp default current_timestamp,
    foreign key (pedido_id) references pedidos(id)
);

create table favoritos (
    usuario_id int not null,
    producto_id int not null,
    primary key (usuario_id, producto_id),
    foreign key (usuario_id) references usuarios(id) on delete cascade,
    foreign key (producto_id) references productos(id) on delete cascade
);