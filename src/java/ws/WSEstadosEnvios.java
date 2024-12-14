
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.EstadosEnvioDAO;
import modelo.pojo.EstadoEnvio;

@Path("estados")
public class WSEstadosEnvios {
  
    @GET
    @Path("todo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstadoEnvio> consultarEnvio() {
        return EstadosEnvioDAO.consultarEnviosAsignados();
    }

}
