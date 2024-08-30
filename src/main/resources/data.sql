CREATE TABLE IF NOT EXISTS concepto_laboral (
    id INT PRIMARY KEY,
    hs_maximo INT,
    hs_minimo INT,
    laborable BOOLEAN,
    nombre VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS empleados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    documento VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_creacion DATE NOT NULL
);

INSERT INTO empleados (id, nombre, apellido, documento, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES (1, 'Juan', 'Pérez', '12345678', 'juan.perez@example.com', '1985-10-10', '2023-08-29', '2023-08-29');

INSERT INTO empleados (id, nombre, apellido, documento, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES (2, 'Ana', 'García', '87654321', 'ana.garcia@example.com', '1990-05-15', '2023-08-29', '2023-08-29');