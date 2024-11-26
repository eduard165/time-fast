package modelo.pojo;

public class RespuestaColaborador {

    private boolean error;
    private String contenido;
    private Colaborador colaborador;

    public RespuestaColaborador() {
    }

    public RespuestaColaborador(boolean error, String contenido, Colaborador colaborador) {
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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
   public String toString() {
        return "RespuestaLoginCliente{" +
                "error=" + error +
                ", contenido='" + contenido + '\'' +
                ", colaborador=" + colaborador +
                '}';
    }
    

}
