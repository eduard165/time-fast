/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo.respuestas;

import modelo.pojo.Unidad;

/**
 *
 * @author eduar
 */
public class RespuestaUnidad {
     private boolean error;
    private String contenido;
    private Unidad unidad;

    public RespuestaUnidad() {
    }

    public RespuestaUnidad(boolean error, String contenido, Unidad unidad) {
        this.error = error;
        this.contenido = contenido;
        this.unidad = unidad;
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

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
    
}
