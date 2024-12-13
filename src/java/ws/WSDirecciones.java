package ws;

import java.util.List;
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
import modelo.pojo.Estado;
import modelo.pojo.Mensaje;
import modelo.pojo.Municipio;
import modelo.pojo.respuestas.RespuestaDireccion;
import utils.ValidacionesDireccion;

@Path("direcciones")
public class WSDirecciones {

    public WSDirecciones() {
    }

    
    @Path("registrar-cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje crearDireccionCliente(Direccion direccion) {
        ValidacionesDireccion.validarDireccion(direccion,1);
        return DireccionesDAO.insertarDireccionCliente(direccion);
    }

    @Path("registrar-origen")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje crearDireccionOrigen(Direccion direccion) {
        ValidacionesDireccion.validarDireccion(direccion, 2);
        return DireccionesDAO.insertarDireccionOrigen(direccion);
    }

    @Path("registrar-destino")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje crearDireccionDestino(Direccion direccion) {
        ValidacionesDireccion.validarDireccion(direccion, 3);
        return DireccionesDAO.insertarDireccionDestino(direccion);
    }

    
    @Path("editar-cliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarDireccionCliente(Direccion direccion) {
        ValidacionesDireccion.validarDireccionEditada(direccion,1);
        return DireccionesDAO.editarDireccionCliente(direccion);
    }

    @Path("editar-origen")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarDireccionOrigen(Direccion direccion) {
        ValidacionesDireccion.validarDireccionEditada(direccion,2);
        return DireccionesDAO.editarDireccionOrigen(direccion);
    }

    @Path("editar-destino")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarDireccionDestino(Direccion direccion) {
        ValidacionesDireccion.validarDireccionEditada(direccion,3);
        return DireccionesDAO.editarDireccionDestino(direccion); 
    }
    @Path("eliminar-cliente/{idCliente}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarDireccionCliente(@PathParam("idCliente") int idCliente) {
        ValidacionesDireccion.validarId(idCliente);
        return DireccionesDAO.eliminarDireccionCliente(idCliente);
    }

    @Path("eliminar-origen/{idEnvioOrigen}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarDireccionOrigen(@PathParam("idEnvioOrigen") int idEnvioOrigen) {
        ValidacionesDireccion.validarId(idEnvioOrigen);
        return DireccionesDAO.eliminarDireccionOrigen(idEnvioOrigen);
    }

    @Path("eliminar-destino/{idEnvioDestino}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarDireccionDestino(@PathParam("idEnvioDestino") int idEnvioDestino) {
        ValidacionesDireccion.validarId(idEnvioDestino);
        return DireccionesDAO.eliminarDireccionDestino(idEnvioDestino);
    }

    @Path("buscar-cliente/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaDireccion buscarDireccionCliente(@PathParam("idCliente") int idCliente) {
        return DireccionesDAO.buscarDireccionCliente(idCliente);
    }

    @Path("buscar-origen/{idEnvioOrigen}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaDireccion buscarDireccionOrigen(@PathParam("idEnvioOrigen") int idEnvioOrigen) {
        return DireccionesDAO.buscarDireccionOrigen(idEnvioOrigen);
    }

    @Path("buscar-destino/{idEnvioDestino}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaDireccion buscarDireccionDestino(@PathParam("idEnvioDestino") int idEnvioDestino) {
        return DireccionesDAO.buscarDireccionDestino(idEnvioDestino);
    }
    
     @Path("obtenerEstados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> obtenerDomicilioPaciente() {
        return DireccionesDAO.obtenerEstados();
    }

    @Path("obtenerMunicipios/{idEstado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Municipio> buscarEmpresa(@PathParam("idEstado") int idEstado) {
        return DireccionesDAO.obtenerMunicipios(idEstado);
    }
}
