-- Procedimiento EliminarCliente
DELIMITER $$
CREATE PROCEDURE EliminarCliente(IN p_idCliente INT)
BEGIN
    DELETE FROM paquetes WHERE idEnvio IN (SELECT idEnvio FROM envios WHERE idCliente = p_idCliente);
    DELETE FROM direcciones WHERE idEnvioOrigen IN (SELECT idEnvio FROM envios WHERE idCliente = p_idCliente) OR idEnvioDestino IN (SELECT idEnvio FROM envios WHERE idCliente = p_idCliente);
    DELETE FROM direcciones WHERE idCliente = p_idCliente;
    DELETE FROM envios WHERE idCliente = p_idCliente;
    DELETE FROM clientes WHERE idCliente = p_idCliente;
END$$
DELIMITER ;

-- Procedimiento EliminarColaborador
DELIMITER $$
CREATE PROCEDURE EliminarColaborador(IN colaboradorId INT)
BEGIN
    DELETE FROM asignaciones_unidad_conductor WHERE idColaborador = colaboradorId;
    DELETE FROM envios WHERE idColaborador = colaboradorId;
    DELETE FROM colaboradores WHERE idColaborador = colaboradorId;
END$$
DELIMITER ;

-- Procedimiento EliminarUnidad
DELIMITER $$
CREATE PROCEDURE EliminarUnidad(IN p_idUnidad INT)
BEGIN
    DELETE FROM asignaciones_unidad_conductor WHERE idUnidad = p_idUnidad;
    DELETE FROM unidades WHERE idUnidad = p_idUnidad;
END$$
DELIMITER ;

-- Procedimiento desactivar_colaborador
DELIMITER $$
CREATE PROCEDURE desactivar_colaborador(IN p_idColaborador INT)
BEGIN
    DELETE FROM asignaciones_unidad_conductor WHERE idColaborador = p_idColaborador;
    UPDATE colaboradores SET activo = 0 WHERE idColaborador = p_idColaborador;
END$$
DELIMITER ;

-- Procedimiento desactivar_unidad
DELIMITER $$
CREATE PROCEDURE desactivar_unidad(IN p_idUnidad INT, IN p_motivo VARCHAR(255))
BEGIN
    DELETE FROM asignaciones_unidad_conductor WHERE idUnidad = p_idUnidad;
    UPDATE unidades SET activo = 0, motivoBaja = p_motivo WHERE idUnidad = p_idUnidad;
END$$
DELIMITER ;