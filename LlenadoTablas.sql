
INSERT INTO roles (nombre) 
VALUES 
    ('Administrador'),
    ('Ejecutivo de tienda'),
    ('Conductor');

INSERT INTO tipo_unidades (nombre)
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
