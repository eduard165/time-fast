package ws;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.EnviosDAO;
import utils.ValidacionesEnvio;
import modelo.pojo.Envio;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaEnvio;

@Path("/envios")
public class WSEnvios {

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEnvio(Envio envio) {
        ValidacionesEnvio.validarEnvio(envio);
        return EnviosDAO.registrarEnvio(envio);
    }

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEnvio(Envio envio) {
        ValidacionesEnvio.validarEnvioEditado(envio);
        return EnviosDAO.editarEnvio(envio);
    }
    
    @GET
    @Path("/consultar/{numeroGuia}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaEnvio consultarEnvio(@PathParam("numeroGuia") String numeroGuia) {
        ValidacionesEnvio.validarNumeroGuia(numeroGuia);
        return EnviosDAO.obtenerEnvioPorGuia(numeroGuia);
    }

    @PUT
    @Path("/asignarConductor")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje asignarConductor(@FormParam("idEnvio") int idEnvio, @FormParam("idColaborador") int idColaborador) {
        ValidacionesEnvio.validarAsignacionDeConductor(idEnvio, idColaborador);
        return EnviosDAO.asignarConductor(idEnvio, idColaborador);
    }

    @PUT
    @Path("/actualizarEstado")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEstatus(@FormParam("idEnvio") int idEnvio, @FormParam("idEstado") int idEstado, @FormParam("idColaborador") int idColaborador, @FormParam("descripcion") String descripcion) {
         if (idEstado == 3 || idEstado == 5) {
            if (descripcion.isEmpty() && descripcion.length() > 225 && descripcion.length() <= 0 ) {
                throw new BadRequestException("La descripcion es obligatoria");
            }
        }
        ValidacionesEnvio.validarCambioEstatus(idEnvio, idEstado,descripcion);
        return EnviosDAO.actualizarEstadoEnvio(idEnvio, idEstado,descripcion);
    }
}
