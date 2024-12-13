package modelo;

import java.util.List;
import modelo.pojo.Paquete;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaPaquetes;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class PaquetesDAO {

    public static Mensaje registrarPaquete(Paquete paquete) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                if (!EnviosDAO.verificarEnvioExistentePorId(paquete.getIdEnvio())){
                    respuesta.setContenido("Envio no valido");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.insert("paquetes.insertarPaquete", paquete);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Paquete registrado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar el paquete. Inténtelo nuevamente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static Mensaje actualizarPaquete(Paquete paquete) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                 if (!EnviosDAO.verificarEnvioExistentePorId(paquete.getIdEnvio())){
                    respuesta.setContenido("Envio no valido");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.update("paquetes.actualizarPaquete", paquete);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Paquete actualizado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar el paquete. Inténtelo nuevamente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static Mensaje eliminarPaquete(int idPaquete) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("paquetes.eliminarPaquete", idPaquete);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Paquete eliminado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar el paquete. Inténtelo nuevamente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static RespuestaPaquetes consultarPaquetePorEnvio(int idEnvio) {
        RespuestaPaquetes respuesta = new RespuestaPaquetes();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                List<Paquete> paquetes = conexionBD.selectList("paquetes.buscarPaquetePorEnvio", idEnvio);

                if (paquetes != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setPaquetes(paquetes);
                } else {
                    respuesta.setContenido("No se encontró el paquete para el envío proporcionado.");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }
     public static List<Paquete> obtenerPaquetes(){
        SqlSession conexionBD = MyBatisUtil.getSession();
        List<Paquete> envios = null;

        if (conexionBD != null) {
            try {
                envios = conexionBD.selectList("paquetes.buscarPaquetes");
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } 
        return envios;
    }
}
