INSERT INTO empleados (nombre, apellido, documento, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES
('John', 'Doe', '12345678', 'john.doe@example.com', '1990-01-01', '2020-01-01', CURRENT_TIMESTAMP),
('Jane', 'Smith', '98765432', 'jane.smith@example.com', '1995-06-15', '2022-03-01', CURRENT_TIMESTAMP),
('Bob', 'Johnson', '11111111', 'bob.johnson@example.com', '1980-03-20', '2015-09-01', CURRENT_TIMESTAMP),
('Maria', 'Garcia', '22222222', 'maria.garcia@example.com', '1992-09-10', '2021-06-01', CURRENT_TIMESTAMP),
('David', 'Lee', '33333333', 'david.lee@example.com', '1985-11-25', '2018-01-01', CURRENT_TIMESTAMP);

-- Create sample conceptos laborales
INSERT INTO conceptos_laborales (hs_maximo, hs_minimo, laborable, nombre)
VALUES
(8, 6, 1, 'Turno Normal'),
(6, 2, 1, 'Turno Extra'),
(0, 0, 0, 'Día Libre');

-- Create sample jornadas laborales
INSERT INTO jornadas_laborales (id_empleado, id_concepto, fecha, hs_trabajadas)
VALUES
(1, 1, '2022-01-01', 8),
(2, 2, '2022-01-02', 10),
(3, 3, '2022-01-03', 12),
(4, 1, '2022-01-04', 14),
(5, 2, '2022-01-05', 16);