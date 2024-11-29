/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo.respuestas;

import modelo.pojo.Asignacion;

/**
 *
 * @author eduar
 */
public class RespuestaAsignacion {
    private boolean error;
    private String contenido;
    private Asignacion asignacion;

    public RespuestaAsignacion() {
    }

    public RespuestaAsignacion(boolean error, String contenido, Asignacion asignacion) {
        this.error = error;
        this.contenido = contenido;
        this.asignacion = asignacion;
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

    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }
    
}
