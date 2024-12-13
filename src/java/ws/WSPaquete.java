package ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.PaquetesDAO;
import utils.ValidacionesPaquete;
import modelo.pojo.Paquete;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaPaquetes;

@Path("/paquetes")
public class WSPaquete {

    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(Paquete paquete) {
        ValidacionesPaquete.validarPaquete(paquete);
        return PaquetesDAO.registrarPaquete(paquete);
    }

    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarPaquete(Paquete paquete) {
        ValidacionesPaquete.validarPaqueteEditado(paquete);
        return PaquetesDAO.actualizarPaquete(paquete);
    }

    @GET
    @Path("consultar/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaquetes consultarPaquete(@PathParam("idEnvio") int idEnvio) {
        ValidacionesPaquete.validarIdPaquete(idEnvio);
        return PaquetesDAO.consultarPaquetePorEnvio(idEnvio);
    }

    @DELETE
    @Path("eliminar/{idPaquete}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") int idPaquete) {
        ValidacionesPaquete.validarIdPaquete(idPaquete);
        return PaquetesDAO.eliminarPaquete(idPaquete);
    }
}
