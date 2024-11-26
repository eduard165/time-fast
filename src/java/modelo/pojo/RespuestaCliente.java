package modelo.pojo;

public class RespuestaCliente {

    private boolean error;
    private String contenido;
    private Cliente cliente;

    public RespuestaCliente() {
    }

    public RespuestaCliente(boolean error, String contenido, Cliente cliente) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
     @Override
    public String toString() {
        return "RespuestaLoginCliente{" +
                "error=" + error +
                ", contenido='" + contenido + '\'' +
                ", cliente=" + cliente +
                '}';
    }

}
