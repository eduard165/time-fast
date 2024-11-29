package utils;

import javax.ws.rs.BadRequestException;
import modelo.pojo.Unidad;

public class ValidacionesUnidad {

    public static void validarUnidad(Unidad unidad) {
        if (unidad == null) {
            throw new BadRequestException("La unidad no puede ser nula.");
        }
        validarMarcaModelo(unidad.getMarca(), unidad.getModelo());
        validarAnio(unidad.getAnio());
        validarVIN(unidad.getVIN());
        validarIdTipoUnidad(unidad.getIdTipoUnidad());
        generarNumeroInterno(unidad); 
    }

    public static void validarUnidadEditada(Unidad unidad) {
        if (unidad == null) {
            throw new BadRequestException("La unidad no puede ser nula.");
        }
        validarId(unidad.getIdUnidad());
        validarMarcaModelo(unidad.getMarca(), unidad.getModelo());
        validarAnio(unidad.getAnio());
        validarVIN(unidad.getVIN());
        generarNumeroInterno(unidad); 
    }

    public static void validarId(Integer idUnidad) {
        if (idUnidad == null || idUnidad <= 0) {
            throw new BadRequestException("El ID de la unidad debe ser obligatorio y mayor que 0.");
        }
    }

    private static void validarMarcaModelo(String marca, String modelo) {
        if (marca == null || marca.isEmpty() || marca.length() > 50 || modelo == null || modelo.isEmpty() || modelo.length() > 50) {
            throw new BadRequestException("La marca y el modelo son obligatorios y no deben exceder los 50 caracteres.");
        }
    }

    private static void validarAnio(Integer anio) {
        if (anio == null || anio < 1900 || anio > 2100) {
            throw new BadRequestException("El año de fabricación debe ser válido y estar entre 1900 y 2100.");
        }
    }

    private static void validarVIN(String vin) {
        if (vin == null || vin.length() != 17) {
            throw new BadRequestException("El VIN debe tener exactamente 17 caracteres.");
        }
    }

    private static void validarIdTipoUnidad(Integer idTipoUnidad) {
        if (idTipoUnidad == null || idTipoUnidad <= 0) {
            throw new BadRequestException("El tipo de unidad debe ser un valor positivo.");
        }
    }

    private static void generarNumeroInterno(Unidad unidad) {
        if (unidad.getNumeroInterno() == null || unidad.getNumeroInterno().isEmpty()) {
            String numeroInternoGenerado = String.valueOf(unidad.getAnio()) + unidad.getVIN().substring(0, 4);
            unidad.setNumeroInterno(numeroInternoGenerado); 
        }
        if (unidad.getNumeroInterno().length() > 20) {
            throw new BadRequestException("El número interno no debe exceder los 20 caracteres.");
        }
    }
}
