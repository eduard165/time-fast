package ws;

import java.util.List;
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

    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(Paquete paquete) {
        ValidacionesPaquete.validarPaquete(paquete);
        return PaquetesDAO.registrarPaquete(paquete);
    }

    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarPaquete(Paquete paquete) {
        ValidacionesPaquete.validarPaqueteEditado(paquete);
        return PaquetesDAO.actualizarPaquete(paquete);
    }

    @Path("consultar/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaPaquetes consultarPaquete(@PathParam("idEnvio") int idEnvio) {
        ValidacionesPaquete.validarIdPaquete(idEnvio);
        return PaquetesDAO.consultarPaquetePorEnvio(idEnvio);
    }

    @Path("eliminar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Mensaje eliminarPaquete(@FormParam("idPaquete") int idPaquete) {
        ValidacionesPaquete.validarIdPaquete(idPaquete);
        return PaquetesDAO.eliminarPaquete(idPaquete);
    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> listaEnvios() {
        return PaquetesDAO.obtenerPaquetes();
    }
}
