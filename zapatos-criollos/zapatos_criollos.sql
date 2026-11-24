-- Script para crear la base de datos del proyecto Zapatos Criollos
-- Ejecútalo una sola vez en MySQL (Workbench o consola)

CREATE DATABASE IF NOT EXISTS zapatos_criollos CHARACTER SET utf8mb4;
USE zapatos_criollos;

-- Tabla de empleados
CREATE TABLE IF NOT EXISTS empleado (
    numero VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    tasa_pago DECIMAL(10,2) NOT NULL
);

-- Tabla de horas trabajadas (archivo de tiempo)
CREATE TABLE IF NOT EXISTS registro_tiempo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    horas DECIMAL(6,2) NOT NULL,
    FOREIGN KEY (numero) REFERENCES empleado(numero)
);