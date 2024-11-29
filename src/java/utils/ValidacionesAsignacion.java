/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.ws.rs.BadRequestException;
import modelo.pojo.Asignacion;

/**
 *
 * @author eduar
 */
public class ValidacionesAsignacion {

    public static void validarAsignacion(Asignacion asignacion) {
        validarIdColaborador(asignacion.getIdColaborador());
        validaridUnidad(asignacion.getIdUnidad());
    }

    public static void validarAsignacionEditada(Asignacion asignacion) {
        validarIdAsignacion(asignacion.getIdAsignacion());
        validarIdColaborador(asignacion.getIdColaborador());
        validaridUnidad(asignacion.getIdUnidad());
    }

    public static void validarIdAsignacion(Integer idAsignacion) {
        if (idAsignacion < 0 || idAsignacion == null) {
            throw new BadRequestException("El idAsignacion no puede ser cero o nulo.");
        }
    }

    private static void validarIdColaborador(Integer idColaborador) {
        if (idColaborador < 0 || idColaborador == null) {
            throw new BadRequestException("El idColaborador no puede ser cero o nulo.");
        }
    }

    private static void validaridUnidad(Integer idUnidad) {
        if (idUnidad < 0 || idUnidad == null) {
            throw new BadRequestException("El idUnidad no puede ser menor a cero o nulo.");
        }
    }
}
