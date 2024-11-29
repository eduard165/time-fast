package modelo.pojo;

import java.util.List;

public class RespuestaColaboradores {

    private boolean error;
    private String contenido;
    private List<Colaborador> colaborador;

    public RespuestaColaboradores() {
    }

    public RespuestaColaboradores(boolean error, String contenido, List<Colaborador> colaborador) {
        this.error = error;
        this.contenido = contenido;
        this.colaborador = colaborador;
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

    public List<Colaborador> getColaborador() {
        return colaborador;
    }

    public void setColaborador(List<Colaborador> colaborador) {
        this.colaborador = colaborador;
    }


}
