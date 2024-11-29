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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import modelo.ColaboradoresDAO;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.RespuestaColaboradores;
import utils.Validaciones;

@Path("colaboradores")
public class WSColaboradores {

    @Context
    private UriInfo context;

    public WSColaboradores() {
    }

    @Path("Hola")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hola() {
        return "saludo";
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(Colaborador colaborador) {
        Validaciones.validarColaborador(colaborador);
        return ColaboradoresDAO.registrarColaborador(colaborador);
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaColaboradores editarColaborador(Colaborador colaborador) {
       Validaciones.validarColaboradorEditado(colaborador);
        return ColaboradoresDAO.editarColaborador(colaborador);
    }

    @Path("eliminar/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaColaboradores eliminarColaborador(@PathParam("idColaborador") int idColaborador) {
        if (idColaborador <= 0) {
            throw new BadRequestException("ID de colaborador no válido");
        }

        return ColaboradoresDAO.eliminarColaborador(idColaborador);
    }

    @Path("buscar-lista/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaColaboradores buscarColaboradores(@PathParam("parametro") String parametro) {
        if (parametro == null || parametro.isEmpty()) {
            throw new BadRequestException("Parámetro de búsqueda no válido");
        }

        // Realizar la búsqueda en la base de datos con el parámetro proporcionado
        return ColaboradoresDAO.buscarColaboradores(parametro);
    }

    @Path("buscar-uno/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaColaboradores buscarColaborador(@PathParam("parametro") String parametro) {
        if (parametro == null || parametro.isEmpty()) {
            throw new BadRequestException("Parámetro de búsqueda no válido");
        }

        return ColaboradoresDAO.buscarColaboradores(parametro);
    }

}
