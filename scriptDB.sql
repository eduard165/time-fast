CREATE SCHEMA timefastdb;
USE timefastdb;

CREATE TABLE roles (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_unidad (
    idTipoUnidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE colaboradores (
    idColaborador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    CURP VARCHAR(18) UNIQUE NOT NULL,
    correoElectronico VARCHAR(100) UNIQUE NOT NULL,
    numeroPersonal VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    idRol INT NOT NULL,
    FOREIGN KEY (idRol) REFERENCES roles(idRol)
);

CREATE TABLE unidades (
    idUnidad INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    VIN VARCHAR(17) UNIQUE NOT NULL,
    idTipoUnidad INT NOT NULL,
    numeroInterno VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (idTipoUnidad) REFERENCES tipo_unidad(idTipoUnidad)
);

CREATE TABLE direcciones (
    idDireccion INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    colonia VARCHAR(100) NOT NULL,
    codigoPostal VARCHAR(5) NOT NULL
);

CREATE TABLE estados_envios (
    idEstado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correoElectronico VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    idDireccion INT NOT NULL,
    FOREIGN KEY (idDireccion) REFERENCES direcciones(idDireccion)
);

CREATE TABLE envios (
    idEnvio INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    numeroGuia VARCHAR(20) UNIQUE NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(225) NOT NULL,
    idDireccionOrigen INT NOT NULL,
    idDireccionDestino INT NOT NULL,
    idEstado INT NOT NULL,
    idColaborador INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idDireccionOrigen) REFERENCES direcciones(idDireccion),
    FOREIGN KEY (idDireccionDestino) REFERENCES direcciones(idDireccion),
    FOREIGN KEY (idEstado) REFERENCES estados_envios(idEstado),
    FOREIGN KEY (idColaborador) REFERENCES colaboradores(idColaborador)
);

CREATE TABLE paquetes (
    idPaquete INT AUTO_INCREMENT PRIMARY KEY,
    idEnvio INT NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    peso DECIMAL(10,2) NOT NULL,
    dimensionesAlto DECIMAL(10,2) NOT NULL,
    dimensionesAncho DECIMAL(10,2) NOT NULL,
    dimensionesProfundidad DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idEnvio) REFERENCES envios(idEnvio)
);

CREATE TABLE asignaciones_unidad_conductor (
    idAsignacion INT AUTO_INCREMENT PRIMARY KEY,
    idUnidad INT NOT NULL,
    idColaborador INT NOT NULL,
    FOREIGN KEY (idUnidad) REFERENCES unidades(idUnidad),
    FOREIGN KEY (idColaborador) REFERENCES colaboradores(idColaborador)
);

INSERT INTO roles (nombre) 
VALUES 
    ('Administrador'),
    ('Ejecutivo de tienda'),
    ('Conductor');

INSERT INTO tipo_unidad (nombre)
VALUES
    ('Gasolina'),
    ('Diesel'),
    ('Electrica'),
    ('Hibrida');

INSERT INTO colaboradores (nombre, apellidoPaterno, apellidoMaterno, CURP, correoElectronico, numeroPersonal, password, idRol)
VALUES
    ('Carlos', 'Hernandez', 'Martinez', 'HELC820505HDFNRR07', 'carlos.hernandez@example.com', 'NP202345678901234567', 'password123', 2);

INSERT INTO unidades (marca, modelo, anio, VIN, idTipoUnidad, numeroInterno)
VALUES 
    ('Toyota', 'Corolla', 2022, 'JTDBU4EE0J3075789', 1, '2022JTD');

INSERT INTO direcciones (calle, numero, colonia, codigoPostal)
VALUES 
    ('Juan Enriquez', '23', 'Centro', '91000');

INSERT INTO estados_envios (nombre) 
VALUES 
    ('Pendiente'),
    ('En transito'),
    ('Detenido'),
    ('Entregado'),
    ('Cancelado');

INSERT INTO clientes (nombre, apellidoPaterno, apellidoMaterno, telefono, correoElectronico, password, idDireccion)
VALUES
    ('Maria', 'Lopez', 'Perez', '5512345678', 'maria.lopez@example.com', 'securepass', 1);

INSERT INTO envios (idCliente, numeroGuia, costo, idDireccionOrigen, idDireccionDestino, idEstado, idColaborador)
VALUES
    (1, 'G1234567890', 150.50, 1, 1, 1, 1);

INSERT INTO paquetes (idEnvio, descripcion, peso, dimensionesAlto, dimensionesAncho, dimensionesProfundidad)
VALUES
    (1, 'Caja mediana', 2.5, 30.0, 20.0, 15.0);

INSERT INTO asignaciones_unidad_conductor (idUnidad, idColaborador)
VALUES
    (1, 1);