package ws;

import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.ColaboradoresDAO;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaColaboradores;
import utils.ValidacionesColaborador;

@Path("colaboradores")
public class WSColaboradores {

    public WSColaboradores() {
    }

    @Path("obtenerTodosActivos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradoresActivos() {
        return ColaboradoresDAO.listaColaboradoresActivos();
    }

    @Path("obtenerTodosInactivos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradoresInactivos() {
        return ColaboradoresDAO.listaColaboradoresInactivos();
    }

    @Path("obtenerConductores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerConductores() {
        return ColaboradoresDAO.listaConductores();
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(Colaborador colaborador) {
        if (colaborador.getIdRol() == 3) {
            if (colaborador.getNumeroLicencia() == null || colaborador.getNumeroLicencia().length() > 100) {
                throw new BadRequestException("Licencia no valida");

            }
        }
        ValidacionesColaborador.validarColaborador(colaborador);
        return ColaboradoresDAO.registrarColaborador(colaborador);
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(Colaborador colaborador) {
        if (colaborador.getIdRol() == 3) {
            if (colaborador.getNumeroLicencia() == null || colaborador.getNumeroLicencia().length() > 100) {
                throw new BadRequestException("Licencia no valida");

            }
        }
        ValidacionesColaborador.validarColaboradorEditado(colaborador);
        return ColaboradoresDAO.editarColaborador(colaborador);
    }

    @Path("desactivar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Mensaje desactivarColaborador(@FormParam("idColaborador") int idColaborador) {
        ValidacionesColaborador.validarId(idColaborador);
        return ColaboradoresDAO.desactivarColaborador(idColaborador);
    }

    @Path("eliminar/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") int idColaborador) {
        ValidacionesColaborador.validarId(idColaborador);
        return ColaboradoresDAO.eliminarColaborador(idColaborador);
    }

    @Path("buscar-lista/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaColaboradores buscarColaboradores(@PathParam("parametro") String parametro) {
        if (parametro == null || parametro.isEmpty()) {
            throw new BadRequestException("Parámetro de búsqueda no válido");
        }
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

    @Path("subir-foto/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idColaborador") Integer idColaborador, byte[] foto) {
        ValidacionesColaborador.validadColaboradorFoto(foto, idColaborador);
        return ColaboradoresDAO.subirFoto(idColaborador, foto);

    }

    @Path("subir-foto-nuevo/{numeroPersonal}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("numeroPersonal") String numeroPersonal, byte[] foto) {
        ValidacionesColaborador.validadColaboradorFotoPorNumeroPersonal(foto, numeroPersonal);
        return ColaboradoresDAO.subirFotoPorNumeroPersonal(numeroPersonal, foto);

    }

    @Path("obtener-foto/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerFoto(@PathParam("idColaborador") Integer idColaborador) {
        ValidacionesColaborador.validarId(idColaborador);
        return ColaboradoresDAO.obtenerFoto(idColaborador);

    }

    @Path("obtener-colaborador/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador colaboradorPorId(@PathParam("idColaborador") Integer idColaborador) {
        ValidacionesColaborador.validarId(idColaborador);
        return ColaboradoresDAO.buscarColaboradorPorId(idColaborador);

    }
}
