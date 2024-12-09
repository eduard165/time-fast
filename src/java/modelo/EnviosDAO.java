package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.pojo.Envio;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaEnvio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EnviosDAO {

    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Mensaje validacion = validarEnvio(envio);
                if (validacion.isError()) {
                    return validacion;
                }
                int filasAfectadas = conexionBD.insert("envios.registrarEnvio", envio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Envío registrado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar el envío. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error: " + e.getMessage());
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
                if (!verificarEnvioExistentePorGuia(envio.getNumeroGuia())) {
                    respuesta.setContenido("El envío no existe.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.update("envios.actualizarEnvio", envio);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Envío actualizado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar el envío. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }
        return respuesta;
    }

    public static Mensaje eliminarEnvio(String numeroGuia) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("envios.eliminarEnvio", numeroGuia);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Envío eliminado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar el envío. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }
        return respuesta;
    }

    public static Mensaje actualizarEstadoEnvio(int idEnvio, int idEstado) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                if (obtenerEnvioPorId(idEnvio) == null) {
                    respuesta.setContenido("El envío no existe.");
                    return respuesta;
                }
                if (idEstado <= 0 || idEstado >= 6) {
                    respuesta.setContenido("El estado del envío es inválido.");
                    return respuesta;
                }
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idEnvio", idEnvio);
                parametros.put("idEstado", idEstado);

                int filasAfectadas = conexionBD.update("envios.actualizarEstadoEnvio", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Estado del envío actualizado");
                } else {
                    respuesta.setContenido("No se pudo actualizar el estado del envío. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }
        return respuesta;
    }

    public static Mensaje asignarConductor(int idEnvio, int idColaborador) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                if (obtenerEnvioPorId(idEnvio) == null) {
                    respuesta.setContenido("El envío no existe.");
                    return respuesta;
                }
                if (!ColaboradoresDAO.verificarColaboradorPorId(idColaborador)) {
                    respuesta.setContenido("El colaborador asignado no existe.");
                    return respuesta;
                }
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idEnvio", idEnvio);
                parametros.put("idColaborador", idColaborador);

                int filasAfectadas = conexionBD.update("envios.asignarConductor", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Conductor asignado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo asignar el conductor. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
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

    public static RespuestaEnvio obtenerEnvioPorGuia(String numeroGuia) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        RespuestaEnvio envio = new RespuestaEnvio();
        envio.setError(true);

        if (conexionBD != null) {
            try {
                Envio envioR = conexionBD.selectOne("envios.consultarEnvioPorNumeroGuia", numeroGuia);
                if (envioR != null) {
                    envio.setEnvio(envioR);
                    envio.setContenido("Envio encontrado");
                    envio.setError(false);
                } else {
                    envio.setContenido("El envio no fue encontrado");
                }
            } catch (Exception e) {
                envio.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            envio.setContenido("No hay conexion con la base de datos");
        }
        return envio;
    }

    public static boolean verificarEnvioExistentePorGuia(String numeroGuia) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Envio envio = conexionBD.selectOne("envios.obtenerEnvioPorNumeroGuia", numeroGuia);
                if (envio != null) {
                    existe = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return existe;
    }

    public static boolean verificarEnvioExistentePorId(int idEnvio) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Envio envio = conexionBD.selectOne("envios.obtenerEnvioPorId", idEnvio);
                if (envio != null) {
                    existe = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return existe;
    }

    public static Mensaje validarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);

        if (verificarEnvioExistentePorGuia(envio.getNumeroGuia())) {
            respuesta.setContenido("El envío ya está registrado.");
            return respuesta;
        }

        if (!ClienteDAO.verificarClientePorId(envio.getIdCliente())) {
            respuesta.setContenido("El cliente no existe.");
            return respuesta;
        }

        if (!DireccionesDAO.verificarDireccionPorId(envio.getIdDireccionDestino())) {
            respuesta.setContenido("La dirección de destino no existe.");
            return respuesta;
        }

        if (!DireccionesDAO.verificarDireccionPorId(envio.getIdDireccionOrigen())) {
            respuesta.setContenido("La dirección de origen no existe.");
            return respuesta;
        }

        if (envio.getIdEstado() <= 0 || envio.getIdEstado() >= 6) {
            respuesta.setContenido("El estado del envío es inválido.");
            return respuesta;
        }

        if (!ColaboradoresDAO.verificarColaboradorPorId(envio.getIdColaborador())) {
            respuesta.setContenido("El colaborador asignado no existe.");
            return respuesta;
        }
        respuesta.setError(false);
        return respuesta;
    }

}
