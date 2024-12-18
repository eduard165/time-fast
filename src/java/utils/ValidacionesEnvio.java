package utils;

import javax.ws.rs.BadRequestException;
import modelo.pojo.Envio;

public class ValidacionesEnvio {

    public static void validarEnvio(Envio envio) {
        if (envio == null ) {
            throw new BadRequestException("El envío no puede ser nulo.");
        }
        validarIdCliente(envio.getIdCliente());
        validarNumeroGuia(envio.getNumeroGuia());
        validarCosto(envio.getCosto());
    }

    public static void validarEnvioEditado(Envio envio) {
        if (envio == null) {
            throw new BadRequestException("El envío no puede ser nulo.");
        }
        validarIdEnvio(envio.getIdEnvio());
    }

    public static void validarCambioEstatus(Integer idEnvio, Integer idEstado, String descripcion) {
       
        validarIdEstadoEnvio(idEstado);
        validarIdEnvio(idEnvio);
    }

    public static void validarAsignacionDeConductor(Integer idEnvio, Integer idColaborador) {
        validarIdColaborador(idColaborador);
        validarIdEnvio(idEnvio);
    }

    public static void validarIdEnvio(Integer idEnvio) {
        if (idEnvio == null || idEnvio <= 0) {
            throw new BadRequestException("El ID del envio debe ser un valor positivo.");
        }
    }

    public static void validarNumeroGuia(String numeroGuia) {
        if (numeroGuia == null || numeroGuia.isEmpty() || numeroGuia.length() > 20) {
            throw new BadRequestException("El número de guía es obligatorio y no debe exceder los 20 caracteres.");
        }
    }

    private static void validarIdCliente(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            throw new BadRequestException("El ID del cliente debe ser un valor positivo.");
        }
    }

    private static void validarCosto(Float costo) {
        if (costo == null || costo < 0) {
            throw new BadRequestException("El costo debe ser un valor positivo.");
        }
    }

  
    private static void validarIdEstadoEnvio(Integer idEstadoEnvio) {
        if (idEstadoEnvio == null || idEstadoEnvio <= 0 || idEstadoEnvio >= 6) {
            throw new BadRequestException("El ID del estado es obligatorio y debe ser válido.");
        }
    }

    private static void validarIdColaborador(Integer idColaborador) {
        if (idColaborador == null || idColaborador <= 0) {
            throw new BadRequestException("El ID del colaborador debe ser obligatorio y mayor que 0.");
        }
    }

}
