
CREATE SCHEMA timefastdb;
USE timefastdb;

CREATE TABLE estados (
    idEstado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45)
);

CREATE TABLE municipios (
    idMunicipio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45),
    idEstado INT,
    FOREIGN KEY (idEstado) REFERENCES estados(idEstado)
);

CREATE TABLE estados_envios (
    idEstadoEnvio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_unidad (
    idTipoUnidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE roles (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
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
    fotografia LONGBLOB NULL,
    FOREIGN KEY (idRol) REFERENCES roles(idRol)
);

CREATE TABLE clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correoElectronico VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
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

CREATE TABLE envios (
    idEnvio INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    numeroGuia VARCHAR(20) UNIQUE NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(225) NOT NULL,
    idEstadoEnvio INT NOT NULL,
    idColaborador INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idEstadoEnvio) REFERENCES estados_envios(idEstadoEnvio),
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

CREATE TABLE direcciones (
    idDireccion INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    colonia VARCHAR(100) NOT NULL,
    codigoPostal VARCHAR(5) NOT NULL,
    idMunicipio INT NOT NULL,
    idCliente INT,
    idEnvioOrigen INT, 
    idEnvioDestino INT, 
    FOREIGN KEY (idMunicipio) REFERENCES municipios(idMunicipio),
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idEnvioOrigen) REFERENCES envios(idEnvio),
    FOREIGN KEY (idEnvioDestino) REFERENCES envios(idEnvio)
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
    
INSERT INTO estados_envios (nombre) 
VALUES 
    ('Pendiente'),
    ('En transito'),
    ('Detenido'),
    ('Entregado'),
    ('Cancelado');
    
INSERT INTO estados (nombre) VALUES
('Aguascalientes'), ('Baja California'), ('Baja California Sur'), ('Campeche'), ('Chiapas'),
('Chihuahua'), ('Coahuila de Zaragoza'), ('Colima'), ('Durango'), ('Guanajuato');

INSERT INTO municipios (nombre, idEstado) VALUES
('Aguascalientes', 1), ('Asientos', 1), ('Calvillo', 1), ('Cosío', 1), ('Jesús María', 1),
('Pabellón de Arteaga', 1), ('Rincón de Romos', 1), ('San José de Gracia', 1),
('Tepezalá', 1), ('El Llano', 1);

INSERT INTO municipios (nombre, idEstado) VALUES
('Tijuana', 2), ('Mexicali', 2), ('Ensenada', 2), ('Rosarito', 2), ('Tecate', 2),
('Playas de Rosarito', 2), ('La Rumorosa', 2), ('San Felipe', 2), ('Puertecitos', 2),
('San Quintín', 2);

INSERT INTO municipios (nombre, idEstado) VALUES
('La Paz', 3), ('Cabo San Lucas', 3), ('San José del Cabo', 3), ('Comondú', 3), ('Loreto', 3),
('Mulegé', 3), ('Santa Rosalía', 3), ('Guerrero Negro', 3), ('Villa Alberto Andrés Alvarado Arámburo', 3),
('Ciudad Constitución', 3);

INSERT INTO municipios (nombre, idEstado) VALUES
('Campeche', 4), ('Calkiní', 4), ('Carmen', 4), ('Champotón', 4), ('Hecelchakán', 4),
('Hopelchén', 4), ('Palizada', 4), ('Tenabo', 4), ('Escárcega', 4), ('Calakmul', 4);

INSERT INTO municipios (nombre, idEstado) VALUES
('Tuxtla Gutiérrez', 5), ('Tapachula', 5), ('San Cristóbal de las Casas', 5),
('Tonalá', 5), ('Chiapa de Corzo', 5), ('Comitán de Domínguez', 5), ('Ocosingo', 5),
('Villaflores', 5), ('Palenque', 5), ('Cintalapa', 5);

INSERT INTO municipios (nombre, idEstado) VALUES
('Chihuahua', 6), ('Juárez', 6), ('Cuauhtémoc', 6), ('Delicias', 6), ('Parral', 6),
('Hidalgo del Parral', 6), ('Nuevo Casas Grandes', 6), ('Camargo', 6), ('Jiménez', 6),
('Meoqui', 6);

INSERT INTO municipios (nombre, idEstado) VALUES
('Saltillo', 7), ('Torreón', 7), ('Monclova', 7), ('Piedras Negras', 7), ('Acuña', 7),
('Matamoros', 7), ('Sabinas', 7), ('Nueva Rosita', 7), ('Frontera', 7), ('Allende', 7);

INSERT INTO municipios (nombre, idEstado) VALUES
('Colima', 8), ('Manzanillo', 8), ('Tecomán', 8), ('Villa de Álvarez', 8), ('Minatitlán', 8),
('Armería', 8), ('Cuauhtémoc', 8), ('Coquimatlán', 8), ('Comala', 8), ('Ixtlahuacán', 8);

INSERT INTO municipios (nombre, idEstado) VALUES
('Durango', 9), ('Gómez Palacio', 9), ('Lerdo', 9), ('Victoria de Durango', 9), ('Canatlán', 9),
('Tlahualilo', 9), ('Guadalupe Victoria', 9), ('San Juan del Río', 9), ('Peñón Blanco', 9),
('Pueblo Nuevo', 9);

INSERT INTO municipios (nombre, idEstado) VALUES
('León', 10), ('Irapuato', 10), ('Celaya', 10), ('Salamanca', 10), ('Guanajuato', 10),
('San Miguel de Allende', 10), ('Acámbaro', 10), ('Silao', 10), ('Dolores Hidalgo', 10),
('Valle de Santiago', 10);