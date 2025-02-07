/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.RolDAO;
import modelo.pojo.Rol;

/**
 *
 * @author eduar
 */
@Path("rol")
public class WSRol {

    @Path("obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> loginColaborador() {
        return RolDAO.listaRoles();
    }
}
