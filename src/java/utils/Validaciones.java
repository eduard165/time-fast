package utils;

import java.util.regex.Pattern;
import javax.ws.rs.BadRequestException;
import modelo.pojo.Colaborador;

public class Validaciones {

    public static void validarColaborador(Colaborador colaborador) {
        if (colaborador == null) {
            throw new BadRequestException("El colaborador no puede ser nulo");
        }
        validarNombreApellido(colaborador.getNombre(), colaborador.getApellidoPaterno(), colaborador.getApellidoMaterno());
        validarCURP(colaborador.getCURP());
        validarCorreoElectronico(colaborador.getCorreoElectronico());
        validarNumeroPersonal(colaborador.getNumeroPersonal());
        validarPassword(colaborador.getPassword());
        validarIdRol(colaborador.getIdRol());

    }

    public static void validarColaboradorEditado(Colaborador colaborador) {
        if (colaborador == null || colaborador.getIdColaborador() != null || colaborador.getIdColaborador() > 0 ) {
            throw new BadRequestException("El colaborador no puede ser nulo");
        }
        validarNombreApellido(colaborador.getNombre(), colaborador.getApellidoPaterno(), colaborador.getApellidoMaterno());
        validarCURP(colaborador.getCURP());
        validarCorreoElectronico(colaborador.getCorreoElectronico());
        validarNumeroPersonal(colaborador.getNumeroPersonal());
        validarPassword(colaborador.getPassword());
        validarIdRol(colaborador.getIdRol());

    }

    private static void validarNombreApellido(String nombre, String apellidoPaterno, String apellidoMaterno) {
        if (nombre == null || nombre.isEmpty() || nombre.length() > 50 || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || apellidoMaterno.length() > 50 || apellidoPaterno.length() > 50) {
            throw new BadRequestException("El nombre debe ser obligatorio y no debe exceder 50 caracteres.");
        }
    }

    private static void validarCURP(String curp) {
        if (curp == null || curp.length() != 18 || !Pattern.matches("[A-Z]{4}[0-9]{6}[A-Z]{6}[0-9]{2}", curp)) {
            throw new BadRequestException("El CURP debe tener 18 caracteres y ser válido.");
        }
    }

    private static void validarCorreoElectronico(String correoElectronico) {
        if (correoElectronico == null || correoElectronico.isEmpty() || correoElectronico.length() > 100 || !isValidEmail(correoElectronico)) {
            throw new BadRequestException("El correo electrónico es obligatorio, debe ser válido y no exceder 100 caracteres.");
        }
    }

    private static void validarNumeroPersonal(String numeroPersonal) {
        if (numeroPersonal == null || numeroPersonal.length() != 20) {
            throw new BadRequestException("El número personal debe tener exactamente 20 caracteres.");
        }
    }

    private static void validarPassword(String password) {
        if (password == null || password.length() < 8 || password.length() > 255) {
            throw new BadRequestException("La contraseña debe tener al menos 8 caracteres y no exceder 255 caracteres.");
        }
    }

    private static void validarIdRol(int idRol) {
        if (idRol <= 0) {
            throw new BadRequestException("El idRol debe ser un valor positivo.");
        }
    }

    private static void validarFotografia(byte[] fotografia) {
        if (fotografia == null || fotografia.length == 0) {
            throw new BadRequestException("La fotografía es obligatoria.");
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
