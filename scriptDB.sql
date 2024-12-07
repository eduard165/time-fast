CREATE SCHEMA timefastdb;
USE timefastdb;

CREATE TABLE tipo_unidad (
  idTipoUnidad INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(20) NOT NULL,
  PRIMARY KEY (idTipoUnidad),
  UNIQUE INDEX tipo (nombre)
);

CREATE TABLE unidades (
  idUnidad INT NOT NULL AUTO_INCREMENT,
  marca VARCHAR(50) NOT NULL,
  modelo VARCHAR(50) NOT NULL,
  anio YEAR NOT NULL,
  VIN VARCHAR(17) NOT NULL,
  idTipoUnidad INT NOT NULL,
  numeroInterno VARCHAR(20) NOT NULL,
  PRIMARY KEY (idUnidad),
  UNIQUE INDEX vin (VIN),
  UNIQUE INDEX numeroInterno (numeroInterno),
  CONSTRAINT unidades_ibfk_1 FOREIGN KEY (idTipoUnidad) REFERENCES tipo_unidad (idTipoUnidad)
);

CREATE TABLE roles (
  idRol INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (idRol)
);

CREATE TABLE colaboradores (
  idColaborador INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidoPaterno VARCHAR(50) NOT NULL,
  apellidoMaterno VARCHAR(50) NULL DEFAULT NULL,
  CURP CHAR(18) NOT NULL,
  correoElectronico VARCHAR(100) NOT NULL,
  numeroPersonal VARCHAR(20) NOT NULL,
  password VARCHAR(255) NOT NULL,
  idRol INT NULL DEFAULT NULL,
  fotografia LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (idColaborador),
  UNIQUE INDEX CURP (CURP),
  UNIQUE INDEX correoElectronico (correoElectronico),
  UNIQUE INDEX numeroPersonal (numeroPersonal),
  INDEX colaboradores_ibfk_1 (idRol),
  CONSTRAINT colaboradores_ibfk_1 FOREIGN KEY (idRol) REFERENCES timefastdb.roles (idRol)
);

CREATE TABLE asignaciones_unidad_conductor (
  idAsignacion INT NOT NULL AUTO_INCREMENT,
  idUnidad INT NOT NULL,
  idColaborador INT NOT NULL,
  PRIMARY KEY (idAsignacion),
  UNIQUE INDEX idColaborador_UNIQUE (idColaborador),
  INDEX idUnidad (idUnidad),
  INDEX asignaciones_unidad_conductor_ibfk_2_idx (idColaborador),
  CONSTRAINT asignaciones_unidad_conductor_ibfk_1 FOREIGN KEY (idUnidad) REFERENCES timefastdb.unidades (idUnidad),
  CONSTRAINT asignaciones_unidad_conductor_ibfk_2 FOREIGN KEY (idColaborador) REFERENCES timefastdb.colaboradores (idColaborador)
);

CREATE TABLE direcciones (
  idDireccion INT NOT NULL AUTO_INCREMENT,
  calle VARCHAR(100) NOT NULL,
  numero VARCHAR(10) NOT NULL,
  colonia VARCHAR(50) NOT NULL,
  codigoPostal CHAR(5) NOT NULL,
  ciudad VARCHAR(100) NULL DEFAULT NULL,
  estado VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (idDireccion)
);

CREATE TABLE clientes (
  idCliente INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidoPaterno VARCHAR(50) NOT NULL,
  apellidoMaterno VARCHAR(50) NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  correoElectronico VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  idDireccion INT NULL DEFAULT NULL,
  PRIMARY KEY (idCliente),
  UNIQUE INDEX correoElectronico (correoElectronico),
  INDEX idDireccion (idDireccion),
  CONSTRAINT clientes_ibfk_1 FOREIGN KEY (idDireccion) REFERENCES timefastdb.direcciones (idDireccion)
);

CREATE TABLE estados_envios (
  idEstadoEnvio INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (idEstadoEnvio)
);

CREATE TABLE envios (
  idEnvio INT NOT NULL AUTO_INCREMENT,
  idCliente INT NOT NULL,
  numeroGuia VARCHAR(50) NOT NULL,
  costo DECIMAL(10,2) NOT NULL,
  idDireccionOrigen INT NOT NULL,
  idDireccionDestino INT NOT NULL,
  idEstado INT NOT NULL,
  idColaborador INT NOT NULL,
  PRIMARY KEY (idEnvio),
  UNIQUE INDEX numeroGuia (numeroGuia),
  INDEX idCliente (idCliente),
  INDEX envios_ibfk_2 (idDireccionOrigen),
  INDEX envios_ibfk_3 (idDireccionDestino),
  INDEX envios_ibk_4_idx (idColaborador),
  INDEX envios_ibk_5_idx (idEstado),
  CONSTRAINT envios_ibfk_1 FOREIGN KEY (idCliente) REFERENCES timefastdb.clientes (idCliente),
  CONSTRAINT envios_ibfk_2 FOREIGN KEY (idDireccionOrigen) REFERENCES timefastdb.direcciones (idDireccion),
  CONSTRAINT envios_ibfk_3 FOREIGN KEY (idDireccionDestino) REFERENCES timefastdb.direcciones (idDireccion),
  CONSTRAINT envios_ibk_4 FOREIGN KEY (idColaborador) REFERENCES timefastdb.colaboradores (idColaborador),
  CONSTRAINT envios_ibk_5 FOREIGN KEY (idEstado) REFERENCES timefastdb.estados_envios (idEstadoEnvio)
);

CREATE TABLE paquetes (
  idPaquete INT NOT NULL AUTO_INCREMENT,
  idEnvio INT NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  peso DECIMAL(10,2) NOT NULL,
  dimensionesAlto DECIMAL(10,2) NOT NULL,
  dimensionesAncho DECIMAL(10,2) NOT NULL,
  dimensionesProfundidad DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (idPaquete),
  INDEX idEnvio (idEnvio),
  CONSTRAINT paquetes_ibfk_1 FOREIGN KEY (idEnvio) REFERENCES timefastdb.envios (idEnvio)
);



CREATE USER 'Eduard'@'localhost' IDENTIFIED BY 'FlowLetal';
GRANT ALL PRIVILEGES ON timefastdb.* TO 'Eduard'@'127.0.0.1';
ALTER USER 'Eduard'@'127.0.0.1' IDENTIFIED BY 'tu_contraseña';
FLUSH PRIVILEGES;

INSERT INTO roles (nombre) 
VALUES 
    ('Administrador'),
    ('Ejecutivo de tienda'),
    ('Conductor');
INSERT INTO tipo_unidad (tipo)
VALUES
    ('Gasolina'),
    ('Diesel'),
    ('Eléctrica'),
    ('Híbrida');

INSERT INTO colaboradores (nombre, apellidoPaterno, apellidoMaterno, CURP, correoElectronico, numeroPersonal, password, idRol)
VALUES
('Carlos', 'Hernández', 'Martínez', 'HELC820505HDFNRR07', 'carlos.hernandez@example.com', 'NP202345678901234567', 'try', 2);
INSERT INTO unidades (marca, modelo, anio, vin, idTipoUnidad, numeroInterno)
VALUES ('Toyota', 'Corolla', 2022, 'JTDBU4EE0J3075789', 1, '2022JTD');

INSERT INTO direcciones (calle,numero,colonia,codigoPostal)
VALUES("Juan Enriquez",23,"Centro",91000);

select * from colaboradores;
select * from roles;
select * from tipo_unidad;
select * from unidades;
select * from Direcciones;
select * from unidades;
select * from asignaciones_unidad_conductor;