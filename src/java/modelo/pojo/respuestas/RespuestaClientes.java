/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo.respuestas;

import java.util.List;
import modelo.pojo.Cliente;

/**
 *
 * @author eduar
 */
public class RespuestaClientes {

    private boolean error;
    private String contenido;
    private List<Cliente> cliente;

    public RespuestaClientes() {
    }

    public RespuestaClientes(boolean error, String contenido, List<Cliente> cliente) {
        this.error = error;
        this.contenido = contenido;
        this.cliente = cliente;
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

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }
    
}
