package utils;

import java.util.regex.Pattern;
import javax.ws.rs.BadRequestException;
import modelo.pojo.Cliente;

public class ValidacionesCliente {

    public static void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new BadRequestException("El cliente no puede ser nulo.");
        }
        validarNombreApellido(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno());
        validarCorreoElectronico(cliente.getCorreoElectronico());
        validarTelefono(cliente.getTelefono());
    }

    public static void validarClienteEditado(Cliente cliente) {
        if (cliente == null) {
            throw new BadRequestException("El cliente no puede ser nulo.");
        }
        validarId(cliente.getIdCliente());
        validarNombreApellido(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno());
        validarCorreoElectronico(cliente.getCorreoElectronico());
        validarTelefono(cliente.getTelefono());
    }

    public static void validarId(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            throw new BadRequestException("El id debe ser obligatorio y no debe ser menor o igual a 0.");
        }
    }

    private static void validarNombreApellido(String nombre, String apellidoPaterno, String apellidoMaterno) {
        if (nombre == null || nombre.isEmpty() || nombre.length() > 50 ||
            apellidoPaterno == null || apellidoPaterno.isEmpty() || apellidoPaterno.length() > 50 ||
            apellidoMaterno == null || apellidoMaterno.isEmpty() || apellidoMaterno.length() > 50) {
            throw new BadRequestException("El nombre y apellidos son obligatorios y no deben exceder los 50 caracteres.");
        }
    }

    private static void validarCorreoElectronico(String correoElectronico) {
        if (correoElectronico == null || correoElectronico.isEmpty() || correoElectronico.length() > 100 || !isEmailValid(correoElectronico)) {
            throw new BadRequestException("El correo electrónico debe ser válido y no exceder los 100 caracteres.");
        }
    }

    private static boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    private static void validarTelefono(String telefono) {
        if (telefono == null || telefono.length() < 10) {
            throw new BadRequestException("El teléfono debe tener exactamente 10 caracteres.");
        }
    }
}
