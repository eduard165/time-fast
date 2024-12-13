package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.WSAsignaciones.class);
        resources.add(ws.WSClientes.class);
        resources.add(ws.WSColaboradores.class);
        resources.add(ws.WSDirecciones.class);
        resources.add(ws.WSEnvios.class);
        resources.add(ws.WSEstadosEnvios.class);
        resources.add(ws.WSLogin.class);
        resources.add(ws.WSPaquete.class);
        resources.add(ws.WSRol.class);
        resources.add(ws.WSUnidades.class);
    }
    
}
