package ws;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import modelo.AutenticacionDAO;
import modelo.pojo.respuestas.RespuestaCliente;
import modelo.pojo.respuestas.RespuestaColaborador;
@Path("login")
public class WSLogin {

 
    public WSLogin() {}

    @Path("colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public RespuestaColaborador loginColaborador(@FormParam("numeroPersonal") String numeroPersonal, @FormParam("password") String password) {
        if (numeroPersonal == null || numeroPersonal.isEmpty() || password == null || password.isEmpty()) {
            throw new BadRequestException("Credenciales no válidas");
        }

        RespuestaColaborador respuesta = AutenticacionDAO.verificarSesionColaborador(numeroPersonal, password);
        if (respuesta == null) {
            throw new NotAuthorizedException("Credenciales incorrectas");
        }

        return respuesta;
    }

    @Path("cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public RespuestaCliente loginCliente(@FormParam("correo") String correo, @FormParam("password") String password) {
        if (correo == null || correo.isEmpty() || password == null || password.isEmpty()) {
            throw new BadRequestException("Credenciales no válidas");
        }

        RespuestaCliente respuesta = AutenticacionDAO.verificarSesionCliente(correo, password);
        if (respuesta == null) {
            throw new NotAuthorizedException("Credenciales incorrectas");
        }

        return respuesta;
    }
}

