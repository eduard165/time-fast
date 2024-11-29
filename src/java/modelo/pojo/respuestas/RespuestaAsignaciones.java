/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo.respuestas;

import java.util.List;
import modelo.pojo.Asignacion;

/**
 *
 * @author eduar
 */
public class RespuestaAsignaciones {

    private boolean error;
    private String contenido;
    private List<Asignacion> asignaciones;

    public RespuestaAsignaciones() {
    }

    public RespuestaAsignaciones(boolean error, String contenido, List<Asignacion> asignaciones) {
        this.error = error;
        this.contenido = contenido;
        this.asignaciones = asignaciones;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

}
