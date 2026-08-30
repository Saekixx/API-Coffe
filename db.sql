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
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);

create table locales (
    id int auto_increment primary key,
    nombre varchar(100) not null,
    direccion varchar(255) not null,
    ciudad varchar(100) not null,
    latitud decimal(10, 8),
    longitud decimal(11, 8),
    hora_apertura time,
    hora_cierre time
);

create table categorias (
    id int auto_increment primary key,
    nombre varchar(50) not null
);

create table productos (
    id int auto_increment primary key,
    categoria_id int not null,
    nombre varchar(100) not null,
    descripcion text,
    precio_base decimal(8, 2) not null,
    imagen_url varchar(255),
    foreign key (categoria_id) references categorias(id)
);

create table medidas (
    id int auto_increment primary key,
    nombre varchar(50) not null, -- ej: pequeño, mediano, grande
    volumen_ml int,
    precio_adicional decimal(8, 2) default 0.00
);

create table grupos_personalizacion (
    id int auto_increment primary key,
    nombre varchar(50) not null,          -- ej: 'tipo de leche', 'crema batida', 'cafeína'
    es_obligatorio boolean default false, -- true: exige elegir | false: opcional
    max_seleccion int default 1           -- 1: selección única (radio) | >1: múltiple (checkbox)
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
    descuento_monto decimal(8, 2) default 0.00,
    activo boolean default true
);

create table pedidos (
    id int auto_increment primary key,
    usuario_id int not null,
    local_id int not null,
    cupon_id int null,
    metodo_entrega enum('EN_LOCAL', 'DELIVERY') not null,
    fecha_programada date,
    hora_programada time,
    subtotal decimal(8, 2) not null,
    descuento decimal(8, 2) default 0.00,
    total decimal(8, 2) not null,
    estado enum('PEDIDO_REALIZADO', 'EN_PREPARACION', 'LISTO', 'EN_CAMINO', 'ENTREGADO', 'CANCELADO') default 'PEDIDO_REALIZADO',
    creado_en timestamp default current_timestamp,
    foreign key (usuario_id) references usuarios(id),
    foreign key (local_id) references locales(id),
    foreign key (cupon_id) references cupones(id)
);

create table detalle_pedidos (
    id int auto_increment primary key,
    pedido_id int not null,
    producto_id int not null,
    medida_id int not null,
    cantidad int not null,
    precio_unitario decimal(8, 2) not null,
    subtotal decimal(8, 2) not null,
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
    metodo enum('TARJETA', 'EFECTIVO', 'PAYPAL') not null,
    proveedor_tarjeta varchar(50), -- ej: visa, mastercard
    ultimos_4_digitos varchar(4),
    monto decimal(8, 2) not null,
    estado_pago enum('PENDIENTE', 'COMPLETADO', 'FALLIDO') default 'COMPLETADO',
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