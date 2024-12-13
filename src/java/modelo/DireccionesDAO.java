package modelo;

import com.sun.glass.events.ViewEvent;
import java.util.List;
import modelo.pojo.Direccion;
import modelo.pojo.Estado;
import modelo.pojo.Mensaje;
import modelo.pojo.Municipio;
import modelo.pojo.respuestas.RespuestaDireccion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class DireccionesDAO {

    public static Mensaje insertarDireccionCliente(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                if (!ClienteDAO.verificarClientePorId(direccion.getIdCliente())) {
                    respuesta.setContenido("Cliente no valido ");
                    return respuesta;
                }
                if (verificarExistenciaDireccionCliente(direccion.getIdCliente())) {
                    respuesta.setContenido("Prueba editando tu direccion");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("direcciones.insertarDireccionCliente", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del cliente registrada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar la dirección del cliente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje insertarDireccionOrigen(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                if (!EnviosDAO.verificarEnvioExistentePorId(direccion.getIdEnvioOrigen())) {
                    respuesta.setContenido("Envio no valido");
                    return respuesta;
                }
                if (verificarExistenciaDireccionOrigen(direccion.getIdEnvioOrigen())) {
                    respuesta.setContenido("Prueba editando la direccion");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("direcciones.insertarDireccionOrigen", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del origen registrada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar la dirección del origen.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje insertarDireccionDestino(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                if (!EnviosDAO.verificarEnvioExistentePorId(direccion.getIdEnvioDestino())) {
                    respuesta.setContenido("Envio no valido");
                    return respuesta;
                }
                if (verificarExistenciaDireccionDestino(direccion.getIdEnvioDestino())) {
                    respuesta.setContenido("Prueba actualizadno el distino");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.insert("direcciones.insertarDireccionDestino", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del destino registrada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar la dirección del destino.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje editarDireccionCliente(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                if (!ClienteDAO.verificarClientePorId(direccion.getIdCliente())) {
                    respuesta.setContenido("Cliente no valido");
                    return respuesta;
                }
                if (!verificarExistenciaDireccionCliente(direccion.getIdCliente())) {
                    respuesta.setContenido("Direccion no existente");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.update("direcciones.editarDireccionCliente", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del cliente actualizada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar la dirección del cliente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje editarDireccionOrigen(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                if (!EnviosDAO.verificarEnvioExistentePorId(direccion.getIdEnvioOrigen())) {
                    respuesta.setContenido("Envio no valido");
                    return respuesta;
                }
                if (!verificarExistenciaDireccionOrigen(direccion.getIdEnvioOrigen())) {
                    respuesta.setContenido("Direccion no existente");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.update("direcciones.editarDireccionOrigen", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del origen actualizada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar la dirección del origen.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje editarDireccionDestino(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                if (!EnviosDAO.verificarEnvioExistentePorId(direccion.getIdEnvioDestino())) {
                    respuesta.setContenido("Envio no valido");
                    return respuesta;
                }
                if (!verificarExistenciaDireccionDestino(direccion.getIdEnvioDestino())) {
                    respuesta.setContenido("Direccion no existente");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.update("direcciones.editarDireccionDestino", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del destino actualizada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar la dirección del destino.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje eliminarDireccionCliente(int idCliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("direcciones.eliminarDireccionCliente", idCliente);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del cliente eliminada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar la dirección del cliente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje eliminarDireccionOrigen(int idEnvioOrigen) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("direcciones.eliminarDireccionOrigen", idEnvioOrigen);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del origen eliminada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar la dirección del origen.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Mensaje eliminarDireccionDestino(int idEnvioDestino) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("direcciones.eliminarDireccionDestino", idEnvioDestino);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección del destino eliminada correctamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar la dirección del destino.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static RespuestaDireccion buscarDireccionCliente(int idCliente) {
        RespuestaDireccion respuesta = new RespuestaDireccion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionCliente", idCliente);
                if (direccion != null) {
                    respuesta.setDireccion(direccion);
                    respuesta.setContenido("Contenido y Dirección encontrada.");
                    respuesta.setError(false);
                } else {
                    respuesta.setContenido("No se encontró la dirección.");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error al buscar la dirección.");
            } finally {
                conexionBD.close();
            }
        }
        if (respuesta == null) {
            respuesta.setContenido("Error en la conexión a la base de datos.");
        }

        return respuesta;
    }

    public static RespuestaDireccion buscarDireccionOrigen(int idEnvioOrigen) {
        RespuestaDireccion respuesta = new RespuestaDireccion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionOrigen", idEnvioOrigen);
                if (direccion != null) {
                    respuesta.setDireccion(direccion);
                    respuesta.setContenido("Contenido y Dirección encontrada.");
                    respuesta.setError(false);
                } else {
                    respuesta.setContenido("No se encontró la dirección.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error al buscar la dirección.");
            } finally {
                conexionBD.close();
            }
        }
        if (respuesta == null) {
            respuesta.setContenido("Error en la conexión a la base de datos.");
        }

        return respuesta;
    }

    public static RespuestaDireccion buscarDireccionDestino(int idEnvioDestino) {
        RespuestaDireccion respuesta = new RespuestaDireccion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionDestino", idEnvioDestino);
                if (direccion != null) {
                    respuesta.setDireccion(direccion);
                    respuesta.setContenido("Contenido y Dirección encontrada.");
                    respuesta.setError(false);
                } else {

                    respuesta.setContenido("No se encontró la dirección.");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error al buscar la dirección.");
            } finally {
                conexionBD.close();
            }
        }
        if (respuesta == null) {
            respuesta.setContenido("Error en la conexión a la base de datos.");
        }

        return respuesta;
    }

    public static boolean verificarExistenciaDireccionCliente(int idCliente) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionCliente", idCliente);
                if (direccion != null) {
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

    public static boolean verificarExistenciaDireccionOrigen(int idEnvioOrigen) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionOrigen", idEnvioOrigen);
                if (direccion != null) {
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

    public static boolean verificarExistenciaDireccionDestino(int idEnvioDestino) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionDestino", idEnvioDestino);
                if (direccion != null) {
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

    public static List<Estado> obtenerEstados() {
        List<Estado> estados = null;

        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                estados = conexionBD.selectList("direcciones.obtenerEstados");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return estados;

    }

    public static List<Municipio> obtenerMunicipios(int idEstado) {
        List<Municipio> municipios = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                municipios = conexionDB.selectList("direcciones.obtenerMunicipios", idEstado);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return municipios;
    }
}
