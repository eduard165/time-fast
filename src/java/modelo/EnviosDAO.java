package modelo;

import java.util.List;
import modelo.pojo.Envio;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EnviosDAO {

    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                // Verifica si el envío ya está registrado (puedes agregar lógica específica)
                if (verificarEnvioExistente(envio.getIdEnvio())) {
                    respuesta.setContenido("El envío ya está registrado.");
                    return respuesta;
                }

                // Registrar el nuevo envío en la base de datos
                int filasAfectadas = conexionBD.insert("envios.insertarEnvio", envio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Envío registrado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar el envío. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.toString());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static Mensaje editarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                // Verifica si el envío existe antes de intentar editarlo
                if (!verificarEnvioExistente(envio.getIdEnvio())) {
                    respuesta.setContenido("El envío no existe.");
                    return respuesta;
                }

                // Actualiza los detalles del envío en la base de datos
                int filasAfectadas = conexionBD.update("envios.actualizarEnvio", envio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Envío actualizado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar el envío. Inténtelo nuevamente.");
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

    public static Mensaje eliminarEnvio(int idEnvio) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                // Eliminar el envío por su ID
                int filasAfectadas = conexionBD.delete("envios.eliminarEnvio", idEnvio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Envío eliminado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar el envío. Inténtelo nuevamente.");
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

    public static List<Envio> obtenerEnvios() {
        SqlSession conexionBD = MyBatisUtil.getSession();
        List<Envio> envios = null;

        if (conexionBD != null) {
            try {
                envios = conexionBD.selectList("envios.obtenerEnvios");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envios;
    }

    public static Envio obtenerEnvioPorId(int idEnvio) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        Envio envio = null;

        if (conexionBD != null) {
            try {
                envio = conexionBD.selectOne("envios.obtenerEnvioPorId", idEnvio);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envio;
    }

    public static boolean verificarEnvioExistente(int idEnvio) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Envio envio = conexionBD.selectOne("envios.verificarEnvioExistente", idEnvio);
                if (envio != null) {
                    existe = true;
                }
            } finally {
                conexionBD.close();
            }
        }

        return existe;
    }
}
