package utils;

import javax.ws.rs.BadRequestException;
import modelo.pojo.Direccion;

public class ValidacionesDireccion {

    public static void validarDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new BadRequestException("La dirección no puede ser nula.");
        }
        validarCalle(direccion.getCalle());
        validarNumero(direccion.getNumero());
        validarCodigoPostal(direccion.getCodigoPostal());

    }

    public static void validarDireccionEditada(Direccion direccion) {
        if (direccion == null) {
            throw new BadRequestException("La dirección no puede ser nula.");
        }
        validarId(direccion.getIdDireccion());
        validarCalle(direccion.getCalle());
        validarNumero(direccion.getNumero());
        validarCodigoPostal(direccion.getCodigoPostal());

    }

    public static void validarId(Integer idDireccion) {
        if (idDireccion == null || idDireccion <= 0) {
            throw new BadRequestException("El id de la dirección debe ser obligatorio y no debe ser menor o igual a 0.");
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

    private static void validarCodigoPostal(String codigoPostal) {
        if (codigoPostal == null || codigoPostal.length() != 5 ) {
            throw new BadRequestException("El código postal debe tener 5 dígitos numéricos.");
        }
    }

   
}
