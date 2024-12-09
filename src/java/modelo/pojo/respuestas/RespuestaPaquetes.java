/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo.respuestas;

import java.util.List;
import modelo.pojo.Paquete;

/**
 *
 * @author eduar
 */
public class RespuestaPaquetes {

    private boolean error;
    private String contenido;
    private List<Paquete> paquetes;

    public RespuestaPaquetes() {
    }

    public RespuestaPaquetes(boolean error, String contenido, List<Paquete> paquetes) {
        this.error = error;
        this.contenido = contenido;
        this.paquetes = paquetes;
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

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

}
