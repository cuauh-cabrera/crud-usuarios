
-- CREA BASE DE DATOS USUARIOS--
CREATE DATABASE usuarios;

-- CREA TABLA DE USUARIO --
CREATE TABLE usuario(
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255) DEFAULT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fecha_creacion DATE NOT NULL DEFAULT (CURRENT_DATE),
    fecha_modificacion DATE NOT NULL DEFAULT (CURRENT_DATE),
    is_active BOOL DEFAULT 1,
    INDEX USING HASH (email)
    )ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- AGREGA REGISTRO A LA TABLA USUARIO --
INSERT INTO usuario (nombre,apellido_paterno,email,fecha_creacion,fecha_modificacion)
VALUES
    ('Jose','Perez','jperez@mail.com','2024-10-11','2024-10-11'),
    ('John','Doe','jdoe@mail.com','2024-10-11','2024-10-11'),
    ('Jane','Doe','jane.doe@mail.com','2024-10-11','2024-10-11');