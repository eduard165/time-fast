/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo.respuestas;

import java.util.List;
import modelo.pojo.Unidad;

/**
 *
 * @author eduar
 */
public class RespuestaUnidades {

    private boolean error;
    private String contenido;
    private List<Unidad> unidades;

    public RespuestaUnidades() {
    }

    public RespuestaUnidades(boolean error, String contenido, List<Unidad> unidades) {
        this.error = error;
        this.contenido = contenido;
        this.unidades = unidades;
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

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

}
