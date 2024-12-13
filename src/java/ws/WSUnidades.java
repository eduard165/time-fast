
package ws;

import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.UnidadesDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.TipoUnidad;
import modelo.pojo.Unidad;
import modelo.pojo.respuestas.RespuestaUnidad;
import modelo.pojo.respuestas.RespuestaUnidades;
import utils.ValidacionesUnidad;

@Path("unidades")
public class WSUnidades {


    public WSUnidades() {
    }

  

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarUnidad(Unidad unidad) {
        ValidacionesUnidad.validarUnidad(unidad);
        return UnidadesDAO.registrarUnidad(unidad);
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarUnidad(Unidad unidad) {
        ValidacionesUnidad.validarUnidadEditada(unidad);
        return UnidadesDAO.editarUnidad(unidad);
    }

    @Path("eliminar/{idUnidad}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarUnidad(@PathParam("idUnidad") int idUnidad) {
        ValidacionesUnidad.validarId(idUnidad);
        return UnidadesDAO.eliminarUnidad(idUnidad);
    }
    @Path("obtener-tipos-unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> loginColaborador() {
        return UnidadesDAO.obtenerTiposDeUnidades();
    }

    @Path("buscar-lista/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaUnidades buscarUnidades(@PathParam("parametro") String parametro) {
        if (parametro == null || parametro.isEmpty()) {
            throw new BadRequestException("Parámetro de búsqueda no válido");
        }

        return UnidadesDAO.buscarUnidades(parametro);
    }

    @Path("buscar-uno/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaUnidad buscarUnidad(@PathParam("parametro") String parametro) {
        if (parametro == null || parametro.isEmpty()) {
            throw new BadRequestException("Parámetro de búsqueda no válido");
        }
        return UnidadesDAO.buscarUnidad(parametro);
    }
    
}
