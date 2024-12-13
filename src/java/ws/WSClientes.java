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
import modelo.ClienteDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaCliente;
import utils.ValidacionesCliente;

@Path("clientes")
public class WSClientes {

    public WSClientes() {
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarCliente(Cliente cliente) {
        ValidacionesCliente.validarCliente(cliente);
        return ClienteDAO.registrarCliente(cliente);
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(Cliente cliente) {
        ValidacionesCliente.validarClienteEditado(cliente);
        return ClienteDAO.editarCliente(cliente);
    }

    @Path("eliminar/{idCliente}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(@PathParam("idCliente") int idCliente) {
        ValidacionesCliente.validarId(idCliente);
        return ClienteDAO.eliminarCliente(idCliente);
    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> buscarClientes() {
        return ClienteDAO.buscarClientes();
    }

    @Path("buscar-uno/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaCliente buscarCliente(@PathParam("parametro") String parametro) {
         if (parametro == null || parametro.isEmpty()) {
            throw new BadRequestException("Parámetro de búsqueda no válido");
        }
        return ClienteDAO.buscarCliente(parametro);
    }
}
