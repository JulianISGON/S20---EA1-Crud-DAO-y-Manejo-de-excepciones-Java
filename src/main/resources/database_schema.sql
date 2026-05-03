-- ==========================================
-- SCRIPT DE CREACIÓN DE BASE DE DATOS
-- SISTEMA DE GESTIÓN DE FUNCIONARIOS
-- ==========================================
-- Propósito: Crear la estructura de BD para el CRUD de funcionarios
-- Incluye: Tablas maestras y tabla principal con relaciones
-- Fecha: 2026-05-01
-- ==========================================

-- 1. CREAR BASE DE DATOS
-- Elimina si existe y crea una nueva (CUIDADO en producción)
DROP DATABASE IF EXISTS db_funcionarios;
CREATE DATABASE IF NOT EXISTS db_funcionarios CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 2. USAR LA BASE DE DATOS CREADA
USE db_funcionarios;

-- ==========================================
-- TABLAS DE REFERENCIA (Lookup Tables)
-- Estas tablas contienen valores predefinidos
-- ==========================================

-- 3. TABLA: estado_civil
-- Descripción: Contiene los posibles estados civiles de un funcionario
-- Ejemplos: Soltero, Casado, Divorciado, Viudo
CREATE TABLE estado_civil (
    id_estado INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL UNIQUE,
    abreviatura VARCHAR(10),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. TABLA: tipo_documento
-- Descripción: Tipos de documento de identidad
-- Ejemplos: Cédula de ciudadanía, Pasaporte, RUT, etc.
CREATE TABLE tipo_documento (
    id_tipo_doc INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL UNIQUE,
    abreviatura VARCHAR(10),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==========================================
-- TABLA PRINCIPAL: funcionarios
-- ==========================================

-- 5. TABLA: funcionarios
-- Descripción: Tabla principal que almacena la información de los funcionarios
-- Relaciones:
--   - Tiene una FK a tipo_documento
--   - Tiene una FK a estado_civil
CREATE TABLE funcionarios (
    id_funcio INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    id_tipo_documento INT NOT NULL,
    id_estado_civil INT NOT NULL,
    salario DECIMAL(12, 2) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Restricciones de integridad referencial
    FOREIGN KEY (id_tipo_documento) REFERENCES tipo_documento(id_tipo_doc) ON DELETE RESTRICT,
    FOREIGN KEY (id_estado_civil) REFERENCES estado_civil(id_estado) ON DELETE RESTRICT,

    -- Índices para mejorar rendimiento en búsquedas
    INDEX idx_numero_documento (numero_documento),
    INDEX idx_apellido_nombre (apellido, nombre),
    INDEX idx_estado_civil (id_estado_civil)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==========================================
-- TABLAS DE INFORMACIÓN RELACIONADA
-- ==========================================

-- 6. TABLA: grupo_familiar
-- Descripción: Información de familiares del funcionario
-- Relación: Muchos familiares pueden pertenecer a un funcionario
CREATE TABLE grupo_familiar (
    id_grupo INT PRIMARY KEY AUTO_INCREMENT,
    id_funcio INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    relacion VARCHAR(50),
    fecha_nacimiento DATE,
    numero_documento VARCHAR(20),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- FK debe referenciar la tabla funcionarios
    FOREIGN KEY (id_funcio) REFERENCES funcionarios(id_funcio) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. TABLA: formacion_academica
-- Descripción: Historial de formación académica del funcionario
-- Relación: Un funcionario puede tener múltiples títulos
CREATE TABLE formacion_academica (
    id_formacion INT PRIMARY KEY AUTO_INCREMENT,
    id_funcio INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    institucion VARCHAR(100),
    ano_graduacion INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- FK debe referenciar la tabla funcionarios
    FOREIGN KEY (id_funcio) REFERENCES funcionarios(id_funcio) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==========================================
-- INSERCIÓN DE DATOS INICIALES
-- ==========================================

-- 8. POBLADO TABLA: estado_civil
INSERT INTO estado_civil (descripcion, abreviatura) VALUES
('Soltero', 'S'),
('Casado', 'C'),
('Divorciado', 'D'),
('Viudo', 'V'),
('Unión Libre', 'UL');

-- 9. POBLADO TABLA: tipo_documento
INSERT INTO tipo_documento (descripcion, abreviatura) VALUES
('Cédula de Ciudadanía', 'CC'),
('Pasaporte', 'PA'),
('RUT', 'RUT'),
('Licencia', 'LI'),
('Cédula Extranjería', 'CE');

-- 10. POBLADO TABLA: funcionarios (Datos de ejemplo)
INSERT INTO funcionarios
(nombre, apellido, numero_documento, id_tipo_documento, id_estado_civil, salario, fecha_ingreso, email, telefono)
VALUES
('Juan', 'Pérez Rodríguez', '1001001001', 1, 2, 2500000, '2020-01-15', 'juan.perez@empresa.com', '3001234567'),
('María', 'García López', '1002002002', 1, 1, 3000000, '2020-06-20', 'maria.garcia@empresa.com', '3001234568'),
('Carlos', 'Martínez Gómez', '1003003003', 1, 3, 2800000, '2019-03-10', 'carlos.martinez@empresa.com', '3001234569'),
('Ana', 'Rodríguez Sánchez', '1004004004', 1, 2, 2600000, '2021-02-05', 'ana.rodriguez@empresa.com', '3001234570'),
('Luis', 'Díaz Herrera', '1005005005', 1, 1, 2700000, '2020-09-18', 'luis.diaz@empresa.com', '3001234571');

-- 11. POBLADO TABLA: grupo_familiar
INSERT INTO grupo_familiar (id_funcio, nombre, relacion, fecha_nacimiento, numero_documento) VALUES
(1, 'Anita Pérez', 'Esposa', '1985-05-12', '1001001002'),
(1, 'Pedro Pérez', 'Hijo', '2010-08-20', '1001001003'),
(1, 'Sofia Pérez', 'Hija', '2012-11-15', '1001001004'),
(2, 'Roberto García', 'Padre', '1955-03-08', '1002002003'),
(3, 'Laura Martínez', 'Esposa', '1992-07-18', '1003003002');

-- 12. POBLADO TABLA: formacion_academica
INSERT INTO formacion_academica (id_funcio, titulo, institucion, ano_graduacion) VALUES
(1, 'Ingeniería de Sistemas', 'Universidad ICESI', 2015),
(1, 'Especialización en Bases de Datos', 'Universidad del Valle', 2018),
(2, 'Administración de Empresas', 'Pontificia Universidad Javeriana', 2017),
(2, 'MBA', 'Universidad EAFIT', 2021),
(3, 'Contabilidad', 'Universidad Autónoma del Cauca', 2016),
(4, 'Derecho', 'Universidad de Antioquia', 2018),
(5, 'Ingeniería Industrial', 'Universidad Tecnológica de Pereira', 2019);

-- ==========================================
-- VERIFICACIÓN FINAL
-- ==========================================

-- Ver la estructura de tablas creadas
SHOW TABLES;

-- Ver estructura de tabla funcionarios
DESCRIBE funcionarios;

-- Contar datos insertados
SELECT COUNT(*) as 'Total Funcionarios' FROM funcionarios;
SELECT COUNT(*) as 'Total Familia' FROM grupo_familiar;
SELECT COUNT(*) as 'Total Formación' FROM formacion_academica;

-- ==========================================
-- FIN DEL SCRIPT
-- ==========================================

