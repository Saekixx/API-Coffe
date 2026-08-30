use covoshcoffe_db;

-- 1. USUARIOS
insert into usuarios (nombre_completo, email, password_hash, proveedor_auth, rol, puntos) values
('Administrador Covosh', 'admin@covosh.com', '$2a$12$eImiTXuWVxfM37uY4JANjOL.8844Z1234567890abcdefghijklm', 'LOCAL', 'ADMIN', 0),
('Carlos Barista', 'barista.carlos@covosh.com', '$2a$12$eImiTXuWVxfM37uY4JANjOL.8844Z1234567890abcdefghijklm', 'LOCAL', 'BARISTA', 15),
('Ana Gómez', 'ana.gomez@gmail.com', '$2a$12$eImiTXuWVxfM37uY4JANjOL.8844Z1234567890abcdefghijklm', 'LOCAL', 'CLIENTE', 120),
('Luis Torres', 'ltorres@gmail.com', null, 'GOOGLE', 'CLIENTE', 45);

-- 2. LOCALES
insert into locales (nombre, direccion, ciudad, latitud, longitud, hora_apertura, hora_cierre) values
('Covosh - Miraflores', 'Av. José Larco 742', 'Lima', -12.12154300, -77.02987100, '07:00:00', '22:00:00'),
('Covosh - San Isidro', 'Av. Víctor Andrés Belaúnde 147', 'Lima', -12.09541200, -77.03512300, '07:30:00', '21:00:00');

-- 3. CATEGORÍAS
insert into categorias (nombre) values
('Cafés Calientes'),
('Bebidas Frías'),
('Postres y Repostería'),
('Sándwiches');

-- 4. PRODUCTOS
insert into productos (categoria_id, nombre, descripcion, precio_base, imagen_url) values
(1, 'Espresso Espresso', 'Extracción intensa de granos seleccionados 100% arábica.', 7.00, 'https://images.covosh.com/p/espresso.png'),
(1, 'Cappuccino Tradicional', 'Espresso balanceado con leche vaporizada y suave capa de espuma.', 11.50, 'https://images.covosh.com/p/cappuccino.png'),
(2, 'Iced Caramel Latte', 'Espresso espresso frío, leche, jarabe de caramelo y hielo.', 14.00, 'https://images.covosh.com/p/iced-caramel.png'),
(3, 'Croissant de Almendras', 'Hojaldre artesanal relleno de crema de almendras horneada.', 9.50, 'https://images.covosh.com/p/croissant.png'),
(4, 'Sándwich Caprese', 'Pan ciabatta, queso mozzarella, tomate y salsa pesto.', 16.00, 'https://images.covosh.com/p/caprese.png');

-- 5. MEDIDAS
insert into medidas (nombre, volumen_ml, precio_adicional) values
('Pequeño (8 oz)', 240, 0.00),
('Mediano (12 oz)', 355, 2.50),
('Grande (16 oz)', 470, 4.00);

-- 6. GRUPOS DE PERSONALIZACIÓN
insert into grupos_personalizacion (nombre, es_obligatorio, max_seleccion) values
('Tipo de Leche', false, 1),
('Nivel de Dulzor', false, 1),
('Coppings y Extras', false, 3);

-- 7. OPCIONES DE PERSONALIZACIÓN
insert into opciones_personalizacion (grupo_id, nombre, precio_adicional) values
(1, 'Leche Entera', 0.00),
(1, 'Leche Descremada', 0.00),
(1, 'Leche de Avena', 3.00),
(1, 'Leche de Almendras', 3.00),
(2, 'Sin Azúcar', 0.00),
(2, '50% Dulce', 0.00),
(2, '100% Dulce (Normal)', 0.00),
(3, 'Crema Batida', 2.00),
(3, 'Shot Extra de Espresso', 3.50);

-- 8. PRODUCTO_GRUPOS (Asociar opciones a productos)
insert into producto_grupos (producto_id, grupo_id) values
(2, 1), (2, 2), (2, 3), -- Cappuccino
(3, 1), (3, 2), (3, 3); -- Iced Latte

-- 9. CUPONES
insert into cupones (codigo, descuento_monto, activo) values
('BIENVENIDA10', 5.00, true),
('PROMOCOVOSH', 3.00, true);

-- 10. PEDIDOS
insert into pedidos (usuario_id, local_id, cupon_id, metodo_entrega, fecha_programada, hora_programada, subtotal, descuento, total, estado) values
(3, 1, 1, 'EN_LOCAL', '2026-08-30', '09:30:00', 16.50, 5.00, 11.50, 'LISTO');

-- 11. DETALLE DE PEDIDOS
insert into detalle_pedidos (pedido_id, producto_id, medida_id, cantidad, precio_unitario, subtotal) values
(1, 2, 2, 1, 14.00, 14.00); -- Cappuccino Mediano (11.50 + 2.50)

-- 12. DETALLE DE PERSONALIZACIONES
insert into detalle_personalizaciones (detalle_pedido_id, opcion_id) values
(1, 3); -- Leche de Avena (+3.00)

-- 13. PAGOS
insert into pagos (pedido_id, metodo, proveedor_tarjeta, ultimos_4_digitos, monto, estado_pago) values
(1, 'TARJETA', 'VISA', '4242', 11.50, 'COMPLETADO');

-- 14. FAVORITOS
insert into favoritos (usuario_id, producto_id) values
(3, 2),
(3, 3);