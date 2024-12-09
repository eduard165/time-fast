package utils;

import javax.ws.rs.BadRequestException;
import modelo.pojo.Paquete;

public class ValidacionesPaquete {

    public static void validarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new BadRequestException("El paquete no puede ser nulo.");
        }
        validarIdEnvio(paquete.getIdEnvio());
        validarDescripcion(paquete.getDescripcion());
        validarPeso(paquete.getPeso());
        validarDimensiones(paquete.getDimensionesAlto(), paquete.getDimensionesAncho(), paquete.getDimensionesProfundidad());
    }

    public static void validarPaqueteEditado(Paquete paquete) {
        if (paquete == null) {
            throw new BadRequestException("El paquete no puede ser nulo.");
        }
        validarIdPaquete(paquete.getIdPaquete());
        validarIdEnvio(paquete.getIdEnvio());
        validarDescripcion(paquete.getDescripcion());
        validarPeso(paquete.getPeso());
        validarDimensiones(paquete.getDimensionesAlto(), paquete.getDimensionesAncho(), paquete.getDimensionesProfundidad());
    }


    public static void validarIdPaquete(Integer idPaquete) {
        if (idPaquete == null || idPaquete <= 0) {
            throw new BadRequestException("El ID del paquete debe ser un valor positivo.");
        }
    }

    private static void validarIdEnvio(Integer idEnvio) {
        if (idEnvio == null || idEnvio <= 0) {
            throw new BadRequestException("El ID del envío debe ser un valor positivo.");
        }
    }

    private static void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty() || descripcion.length() > 255) {
            throw new BadRequestException("La descripción del paquete es obligatoria y no debe exceder los 255 caracteres.");
        }
    }

    private static void validarPeso(Float peso) {
        if (peso == null || peso < 0) {
            throw new BadRequestException("El peso del paquete debe ser un valor positivo.");
        }
    }

    private static void validarDimensiones(Float alto, Float ancho, Float profundidad) {
        if (alto == null || alto <= 0) {
            throw new BadRequestException("La dimensión de alto debe ser un valor positivo.");
        }
        if (ancho == null || ancho <= 0) {
            throw new BadRequestException("La dimensión de ancho debe ser un valor positivo.");
        }
        if (profundidad == null || profundidad <= 0) {
            throw new BadRequestException("La dimensión de profundidad debe ser un valor positivo.");
        }
    }
}
