package utils;

import javax.ws.rs.BadRequestException;
import modelo.pojo.Direccion;

public class ValidacionesDireccion {

    public static void validarDireccion(Direccion direccion, int tipo) {
        if (direccion == null) {
            throw new BadRequestException("La dirección no puede ser nula.");
        }
        validarCalle(direccion.getCalle());
        validarNumero(direccion.getNumero());
        validarColonia(direccion.getColonia());
        validarCodigoPostal(direccion.getCodigoPostal());
        if (tipo == 1 && direccion.getIdCliente() == null) {
            throw new BadRequestException("La dirección debe estar asociada a un cliente.");

        }
        if (tipo == 2 && direccion.getIdEnvioOrigen() == null) {
            throw new BadRequestException("La dirección debe estar asociada a un origen .");

        }
        if (tipo == 3 && direccion.getIdEnvioDestino() == null) {
            throw new BadRequestException("La dirección debe estar asociada a un destino.");

        }

    }

    public static void validarDireccionEditada(Direccion direccion, int tipo) {
        if (direccion == null) {
            throw new BadRequestException("La dirección no puede ser nula.");
        }
        validarCalle(direccion.getCalle());
        validarNumero(direccion.getNumero());
        validarColonia(direccion.getColonia());
        validarCodigoPostal(direccion.getCodigoPostal());

        if (tipo == 1 && direccion.getIdCliente() == null) {
            throw new BadRequestException("La dirección debe estar asociada a un cliente.");

        }
        if (tipo == 2 && direccion.getIdEnvioOrigen() == null) {
            throw new BadRequestException("La dirección debe estar asociada a un origen .");

        }
        if (tipo == 3 && direccion.getIdEnvioDestino() == null) {
            throw new BadRequestException("La dirección debe estar asociada a un destino.");

        }
    }

    public static void validarId(Integer idDireccion) {
        if (idDireccion == null || idDireccion <= 0) {
            throw new BadRequestException("El id de la dirección debe ser obligatorio y mayor a 0.");
        }
    }

    private static void validarCalle(String calle) {
        if (calle == null || calle.isEmpty() || calle.length() > 100) {
            throw new BadRequestException("La calle es obligatoria y no debe exceder los 100 caracteres.");
        }
    }

    private static void validarNumero(String numero) {
        if (numero == null || numero.isEmpty() || numero.length() > 10) {
            throw new BadRequestException("El número de la dirección es obligatorio y no debe exceder los 10 caracteres.");
        }
    }

    private static void validarColonia(String colonia) {
        if (colonia == null || colonia.isEmpty() || colonia.length() > 50) {
            throw new BadRequestException("La colonia es obligatoria y no debe exceder los 50 caracteres.");
        }
    }

    private static void validarCodigoPostal(String codigoPostal) {
        if (codigoPostal == null || !codigoPostal.matches("\\d{5}")) {
            throw new BadRequestException("El código postal debe tener exactamente 5 dígitos numéricos.");
        }
    }
}
