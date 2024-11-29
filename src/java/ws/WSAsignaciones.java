package ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.AsignacionesDAO;
import modelo.pojo.Asignacion;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaAsignacion;
import modelo.pojo.respuestas.RespuestaAsignaciones;
import utils.ValidacionesAsignacion;

@Path("asignaciones")
public class WSAsignaciones {

    public WSAsignaciones() {
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarAsignacion(Asignacion asignacion) {
        ValidacionesAsignacion.validarAsignacion(asignacion);
        return AsignacionesDAO.asignarVehiculoAConductor(asignacion);
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarAsignacion(Asignacion asignacion) {
        ValidacionesAsignacion.validarAsignacionEditada(asignacion);
        return AsignacionesDAO.editarAsignacion(asignacion);
    }

    @Path("eliminar/{idAsignacion}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarAsignacion(@PathParam("idAsignacion") int idAsignacion) {
        ValidacionesAsignacion.validarIdAsignacion(idAsignacion);
        return AsignacionesDAO.eliminarAsignacion(idAsignacion);
    }

    @Path("buscar-lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaAsignaciones buscarAsignaciones() {
        return AsignacionesDAO.obtenerAsignaciones();
    }

    @Path("buscar-uno/{idAsinacion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaAsignacion buscarAsignacion(@PathParam("idAsinacion") Integer idAsinacion) {
        ValidacionesAsignacion.validarIdAsignacion(idAsinacion);
        return AsignacionesDAO.obtenerAsignacionPorId(idAsinacion);
    }
}
