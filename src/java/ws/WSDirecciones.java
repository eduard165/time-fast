package ws;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.DireccionesDAO;
import modelo.pojo.Direccion;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaDireccion;
import utils.ValidacionesDireccion;

@Path("direcciones")
public class WSDirecciones {

    public WSDirecciones() {
    }

    @Path("Hola")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hola() {
        return "Hola, servicio de direcciones funcionando";
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarDireccion(Direccion direccion) {
        ValidacionesDireccion.validarDireccion(direccion);
        return DireccionesDAO.registrarDireccion(direccion);  // Método que realiza la inserción en la base de datos
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarDireccion(Direccion direccion) {
        ValidacionesDireccion.validarDireccionEditada(direccion);
        return DireccionesDAO.editarDireccion(direccion);  
    }

    @Path("eliminar/{idDireccion}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarDireccion(@PathParam("idDireccion") int idDireccion) {
        ValidacionesDireccion.validarId(idDireccion);
        return DireccionesDAO.eliminarDireccion(idDireccion);  // Método que elimina la dirección de la base de datos
    }

    @Path("buscar-uno/{idDireccion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaDireccion buscarDireccion(@PathParam("idDireccion") int idDireccion) {
        if (idDireccion <= 0) {
            throw new BadRequestException("ID de dirección no válido.");
        }
        return DireccionesDAO.buscarDireccion(idDireccion);  
    }
}
