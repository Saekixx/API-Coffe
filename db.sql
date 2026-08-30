drop database if exists CovoshCoffe_db;
create database if not exists CovoshCoffe_db;
use CovoshCoffe_db;

create table roles(
	
);

create table usuarios (
    id int auto_increment primary key,
    nombre_completo varchar(150) not null,
    email varchar(150) unique,
    password_hash varchar(255) null, -- Opcional para usuarios que entran por OAuth
    proveedor_auth enum('LOCAL', 'GOOGLE', 'FACEBOOK', 'APPLE') default 'LOCAL',
    proveedor_id varchar(255) null, -- ID único retornado por Google/FB/Apple para vincular la cuenta
    puntos int default 0,
    rol enum('ADMIN','CLIENTE','BARISTA'),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);