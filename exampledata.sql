use covoshcoffe_db;

-- 1. DISTRITOS
insert into distritos (detalle) values
('Miraflores'),
('San Isidro'),
('Surco');

-- 2. USUARIOS
insert into usuarios (nombre_completo, email, password_hash, proveedor_auth, rol, puntos) values
('Administrador Covosh', 'admin@covosh.com', '$2a$12$eImiTXuWVxfM37uY4JANjOL.8844Z1234567890abcdefghijklm', 'LOCAL', 'ADMIN', 0),
('Carlos Barista', 'barista.carlos@covosh.com', '$2a$12$eImiTXuWVxfM37uY4JANjOL.8844Z1234567890abcdefghijklm', 'LOCAL', 'BARISTA', 15),
('Ana Gómez', 'ana.gomez@gmail.com', '$2a$12$eImiTXuWVxfM37uY4JANjOL.8844Z1234567890abcdefghijklm', 'LOCAL', 'CLIENTE', 120),
('Luis Torres', 'ltorres@gmail.com', null, 'GOOGLE', 'CLIENTE', 45);

-- 3. LOCALES
insert into locales (razon_social, direccion, idDistrito, horario, latitud, longitud) values
('Covosh - Miraflores', 'Av. José Larco 742', 1, '07:00:00 - 22:00:00', -12.12154300, -77.02987100),
('Covosh - San Isidro', 'Av. Víctor Andrés Belaúnde 147', 2, '07:30:00 - 21:00:00', -12.09541200, -77.03512300);

-- 4. CATEGORÍAS
insert into categorias (nombre) values
('Cafés Calientes'),
('Bebidas Frías'),
('Postres y Repostería'),
('Sándwiches');

-- 5. PRODUCTOS
insert into productos (categoria_id, nombre, descripcion, precio_base, imagen_url) values
(1, 'Espresso', 'Extracción intensa de granos seleccionados 100% arábica.', 7.00, 'https://silyqigsqwgsvbsawpfs.supabase.co/storage/v1/object/public/productos/7c704591-fa54-4635-935c-504063464fff.png'),
(1, 'Cappuccino Tradicional', 'Espresso balanceado con leche vaporizada y suave capa de espuma.', 11.50, 'https://images.covosh.com/p/cappuccino.png'),
(2, 'Iced Caramel Latte', 'Espresso frío, leche, jarabe de caramelo y hielo.', 14.00, 'https://images.covosh.com/p/iced-caramel.png'),
(3, 'Croissant de Almendras', 'Hojaldre artesanal relleno de crema de almendras horneada.', 9.50, 'https://images.covosh.com/p/croissant.png'),
(4, 'Sándwich Caprese', 'Pan ciabatta, queso mozzarella, tomate y salsa pesto.', 16.00, 'https://images.covosh.com/p/caprese.png');

-- 6. MEDIDAS
insert into medidas (nombre, volumen_ml, precio_adicional) values
('Pequeño (8 oz)', 240, 0.00),
('Mediano (12 oz)', 355, 2.50),
('Grande (16 oz)', 470, 4.00);

-- 7. GRUPOS DE PERSONALIZACIÓN
insert into grupos_personalizacion (id, nombre, es_obligatorio, max_seleccion) values
(1, 'Leche', true, 1),
(2, 'Crema Batida', true, 1),
(3, 'Cafeína', false, 1);

-- 8. OPCIONES DE PERSONALIZACIÓN
insert into opciones_personalizacion (grupo_id, nombre, precio_adicional) values
-- Grupo 1: Leche
(1, 'Leche entera', 0.00),
(1, 'Leche sin lactosa', 0.00),
(1, 'Leche de soya', 0.00),
(1, 'Leche descremada', 0.00),
(1, 'Leche de almendras', 0.70),
(1, 'Leche de avena', 0.70),

-- Grupo 2: Crema Batida
(2, 'Sin crema batida', 0.00),
(2, 'Con crema batida', 0.50),

-- Grupo 3: Cafeína
(3, '¿Sin cafeína?', 0.00);

-- 9. PRODUCTO_GRUPOS
insert into producto_grupos (producto_id, grupo_id) values
(2, 1), (2, 2), (2, 3), -- Cappuccino
(3, 1), (3, 2), (3, 3); -- Iced Caramel Latte

-- 10. CUPONES
insert into cupones (codigo, descuento, fecha_expiracion, activo) values
('BIENVENIDA10', 5.00, '2026-12-31 23:59:59', true),
('PROMOCOVOSH', 3.00, '2026-12-31 23:59:59', true);

-- 11. PEDIDOS
-- Se corrigieron los campos fecha_programada y hora_programada por fecha_entrega (timestamp)
insert into pedidos (usuario_id, local_id, cupon_id, metodo_entrega, fecha_entrega, subtotal, descuento, total, items_total, estado) values
(3, 1, 1, 'EN_LOCAL', '2026-08-30 09:30:00', 14.70, 5.00, 9.70, 1, 'LISTO');

-- 12. DETALLE DE PEDIDOS
-- Se quitó el campo subtotal ya que es una columna generada automáticamente (GENERATED ALWAYS AS)
insert into detalle_pedidos (pedido_id, producto_id, medida_id, cantidad, precio_unitario) values
(1, 2, 2, 1, 14.70);

-- 13. DETALLE DE PERSONALIZACIONES
insert into detalle_personalizaciones (detalle_pedido_id, opcion_id) values
(1, 6),
(1, 7);

-- 14. PAGOS
-- Se corrigió el valor del método a 'TARJETA_CREDITO' y el campo proveedor_tarjeta a proveedor
insert into pagos (pedido_id, metodo, proveedor, ultimos_4_digitos, monto, estado_pago) values
(1, 'TARJETA_CREDITO', 'VISA', '4242', 9.70, 'COMPLETADO');

-- 15. FAVORITOS
insert into favoritos (usuario_id, producto_id) values
(3, 2),
(3, 3);