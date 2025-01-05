CREATE SCHEMA timefastdb;
USE timefastdb;

-- Tabla tipos_unidades
CREATE TABLE IF NOT EXISTS tipos_unidades (
  idTipoUnidad INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (idTipoUnidad)
);

-- Tabla unidades
CREATE TABLE IF NOT EXISTS unidades (
  idUnidad INT NOT NULL AUTO_INCREMENT,
  marca VARCHAR(50) NOT NULL,
  modelo VARCHAR(50) NOT NULL,
  anio INT NOT NULL,
  VIN VARCHAR(17) NOT NULL,
  idTipoUnidad INT NOT NULL,
  numeroInterno VARCHAR(50) NOT NULL,
  activo TINYINT(1) NOT NULL DEFAULT '1',
  motivoBaja VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (idUnidad),
  UNIQUE INDEX VIN (VIN ASC),
  INDEX idTipoUnidad (idTipoUnidad ASC),
  CONSTRAINT unidades_ibfk_1 FOREIGN KEY (idTipoUnidad) REFERENCES tipos_unidades (idTipoUnidad)
);

-- Tabla roles
CREATE TABLE IF NOT EXISTS roles (
  idRol INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (idRol)
);

-- Tabla colaboradores
CREATE TABLE IF NOT EXISTS colaboradores (
  idColaborador INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidoPaterno VARCHAR(50) NOT NULL,
  apellidoMaterno VARCHAR(50) NOT NULL,
  CURP VARCHAR(18) NOT NULL,
  correoElectronico VARCHAR(100) NOT NULL,
  numeroPersonal VARCHAR(20) NOT NULL,
  password VARCHAR(100) NOT NULL,
  idRol INT NOT NULL,
  fotografia LONGBLOB NULL DEFAULT NULL,
  activo TINYINT(1) NOT NULL DEFAULT '1',
  numeroLicencia VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (idColaborador),
  UNIQUE INDEX CURP (CURP ASC),
  UNIQUE INDEX correoElectronico (correoElectronico ASC),
  UNIQUE INDEX numeroPersonal (numeroPersonal ASC),
  INDEX idRol (idRol ASC),
  CONSTRAINT colaboradores_ibfk_1 FOREIGN KEY (idRol) REFERENCES roles (idRol)
);

-- Tabla asignaciones_unidad_conductor
CREATE TABLE IF NOT EXISTS asignaciones_unidad_conductor (
  idAsignacion INT NOT NULL AUTO_INCREMENT,
  idUnidad INT NOT NULL,
  idColaborador INT NOT NULL,
  PRIMARY KEY (idAsignacion),
  UNIQUE INDEX idUnidad_UNIQUE (idUnidad ASC),
  UNIQUE INDEX idColaborador_UNIQUE (idColaborador ASC),
  CONSTRAINT asignaciones_unidad_conductor_ibfk_1 FOREIGN KEY (idUnidad) REFERENCES unidades (idUnidad),
  CONSTRAINT asignaciones_unidad_conductor_ibfk_2 FOREIGN KEY (idColaborador) REFERENCES colaboradores (idColaborador)
);

-- Tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
  idCliente INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidoPaterno VARCHAR(50) NOT NULL,
  apellidoMaterno VARCHAR(50) NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  correoElectronico VARCHAR(100) NOT NULL,
  activo TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (idCliente),
  UNIQUE INDEX correoElectronico (correoElectronico ASC)
);

-- Tabla estados
CREATE TABLE IF NOT EXISTS estados (
  idEstado INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (idEstado)
);

-- Tabla municipios
CREATE TABLE IF NOT EXISTS municipios (
  idMunicipio INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NULL DEFAULT NULL,
  idEstado INT NULL DEFAULT NULL,
  PRIMARY KEY (idMunicipio),
  INDEX idEstado (idEstado ASC),
  CONSTRAINT municipios_ibfk_1 FOREIGN KEY (idEstado) REFERENCES estados (idEstado)
);

-- Tabla estados_envios
CREATE TABLE IF NOT EXISTS estados_envios (
  idEstadoEnvio INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (idEstadoEnvio)
);

-- Tabla envios
CREATE TABLE IF NOT EXISTS envios (
  idEnvio INT NOT NULL AUTO_INCREMENT,
  idCliente INT NOT NULL,
  numeroGuia VARCHAR(20) NOT NULL,
  costo DECIMAL(10,2) NOT NULL,
  descripcion VARCHAR(225) NOT NULL,
  idEstadoEnvio INT NOT NULL,
  idColaborador INT NULL DEFAULT NULL,
  PRIMARY KEY (idEnvio),
  UNIQUE INDEX numeroGuia (numeroGuia ASC ),
  INDEX idEstadoEnvio (idEstadoEnvio ASC),
  INDEX envios_ibfk_3 (idColaborador ASC),
  INDEX fk_envios_clientes (idCliente ASC),
  CONSTRAINT envios_ibfk_1 FOREIGN KEY (idCliente) REFERENCES clientes (idCliente),
  CONSTRAINT envios_ibfk_2 FOREIGN KEY (idEstadoEnvio) REFERENCES estados_envios (idEstadoEnvio),
  CONSTRAINT envios_ibfk_3 FOREIGN KEY (idColaborador) REFERENCES colaboradores (idColaborador)
);

-- Tabla direcciones
CREATE TABLE IF NOT EXISTS direcciones (
  idDireccion INT NOT NULL AUTO_INCREMENT,
  calle VARCHAR(100) NOT NULL,
  numero VARCHAR(10) NOT NULL,
  colonia VARCHAR(100) NOT NULL,
  codigoPostal VARCHAR(5) NOT NULL,
  idMunicipio INT NOT NULL,
  idCliente INT NULL DEFAULT NULL,
  idEnvioOrigen INT NULL DEFAULT NULL,
  idEnvioDestino INT NULL DEFAULT NULL,
  PRIMARY KEY (idDireccion),
  INDEX direcciones_ibfk_2 (idCliente ASC),
  INDEX direcciones_ibfk_1 (idMunicipio ASC),
  CONSTRAINT direcciones_ibfk_1 FOREIGN KEY (idMunicipio) REFERENCES municipios (idMunicipio),
  CONSTRAINT direcciones_ibfk_2 FOREIGN KEY (idCliente) REFERENCES clientes (idCliente),
  CONSTRAINT direcciones_ibfk_3 FOREIGN KEY (idEnvioOrigen) REFERENCES envios (idEnvio),
  CONSTRAINT direcciones_ibfk_4 FOREIGN KEY (idEnvioDestino) REFERENCES envios (idEnvio)
);

-- Tabla paquetes
CREATE TABLE IF NOT EXISTS paquetes (
  idPaquete INT NOT NULL AUTO_INCREMENT,
  idEnvio INT NULL DEFAULT NULL,
  descripcion VARCHAR(255) NOT NULL,
  peso DECIMAL(10,2) NOT NULL,
  dimensionesAlto DECIMAL(10,2) NOT NULL,
  dimensionesAncho DECIMAL(10,2) NOT NULL,
  dimensionesProfundidad DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (idPaquete),
  INDEX paquetes_ibfk_1 (idEnvio ASC),
  CONSTRAINT fk_paquetes_envios FOREIGN KEY (idEnvio) REFERENCES envios (idEnvio)
);