package modelo;

import java.util.List;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaCliente;
import modelo.pojo.respuestas.RespuestaClientes;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ClienteDAO {

    public static Mensaje registrarCliente(Cliente cliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                if (!buscarCliente(cliente.getCorreoElectronico()).isError()) {
                    respuesta.setContenido("El correo electrónico ya está registrado.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("clientes.registrarCliente", cliente);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Cliente registrado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar al cliente. Inténtelo nuevamente.");
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

    public static Mensaje editarCliente(Cliente cliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                RespuestaCliente correo = buscarCliente(cliente.getCorreoElectronico());

                if (!correo.isError() && correo.getCliente().getIdCliente() != cliente.getIdCliente()) {
                    respuesta.setContenido("El correo electrónico ya está registrado.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.update("clientes.editarCliente", cliente);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Cliente editado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo editar al cliente. Inténtelo nuevamente.");
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

    public static Mensaje eliminarCliente(int idCliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                // Eliminar en las tablas relacionadas
                int filasAfectadasCliente = conexionBD.delete("clientes.eliminarCliente", idCliente);

                // Confirmar la transacción si todo fue exitoso
                if (filasAfectadasCliente > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setContenido("Cliente eliminado exitosamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setContenido("No se pudo eliminar al cliente. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static List<Cliente> buscarClientes() {
        List<Cliente> respuesta = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                respuesta = conexionBD.selectList("clientes.buscarClientes");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static RespuestaCliente buscarCliente(String parametro) {
        RespuestaCliente respuesta = new RespuestaCliente();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Cliente cliente = conexionBD.selectOne("clientes.buscarCliente", parametro);

                if (cliente != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setCliente(cliente);
                } else {
                    respuesta.setContenido("No se encontraron clientes con el correo proporcionado.");
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

    public static boolean verificarClientePorId(int idCliente) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Cliente cliente = conexionBD.selectOne("clientes.buscarClientePorId", idCliente);
                if (cliente != null) {
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

    public static Cliente obtenerClientePorId(int idCliente) {
        Cliente cliente = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                cliente = conexionBD.selectOne("clientes.buscarClientePorId", idCliente);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return cliente;
    }
}
