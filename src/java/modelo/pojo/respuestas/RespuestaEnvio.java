
package modelo.pojo.respuestas;

import modelo.pojo.Envio;

public class RespuestaEnvio {

    private boolean error;
    private String contenido;
    private Envio envio;

    public RespuestaEnvio() {
    }

    public RespuestaEnvio(boolean error, String contenido, Envio envio) {
        this.error = error;
        this.contenido = contenido;
        this.envio = envio;
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

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
    
    

}
