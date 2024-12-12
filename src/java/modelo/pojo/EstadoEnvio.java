/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author eduar
 */
public class EstadoEnvio {
    private Integer idEstadoEnvio;
    private String nombre;

    public EstadoEnvio() {
    }

    public EstadoEnvio(Integer idEstadoEnvio, String nombre) {
        this.idEstadoEnvio = idEstadoEnvio;
        this.nombre = nombre;
    }

    public Integer getIdEstado() {
        return idEstadoEnvio;
    }

    public void setIdEstado(Integer idEstadoEnvio) {
        this.idEstadoEnvio = idEstadoEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
