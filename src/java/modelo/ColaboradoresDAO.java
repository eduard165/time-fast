package modelo;

import java.util.List;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.RespuestaColaborador;
import modelo.pojo.RespuestaColaboradores;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ColaboradoresDAO {

    public static Mensaje registrarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {

                if (!buscarColaboradoresExactos(colaborador.getCURP()).isError()) {
                    respuesta.setContenido("El CURP ya esta registrado");
                    return respuesta;
                }
                if (!buscarColaboradoresExactos(colaborador.getCorreoElectronico()).isError()) {
                    respuesta.setContenido("El Correo electrónico ya está registrado");
                    return respuesta;
                }

                if (!buscarColaboradoresExactos(colaborador.getNumeroPersonal()).isError()) {
                    respuesta.setContenido("El NP ya está registrado");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("colaboradores.registrarColaborador", colaborador);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Colaborador registrado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar al colaborador. Inténtelo nuevamente.");
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

    public static RespuestaColaboradores editarColaborador(Colaborador colaborador) {
        RespuestaColaboradores respuesta = new RespuestaColaboradores();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                RespuestaColaborador curp = buscarColaboradoresExactos(colaborador.getCURP());
                RespuestaColaborador correo = buscarColaboradoresExactos(colaborador.getCorreoElectronico());

                if (!curp.isError() && curp.getColaborador().getIdColaborador() != colaborador.getIdColaborador()) {
                    respuesta.setContenido("El CURP ya esta registrado");
                    return respuesta;
                }

                if (!correo.isError() && correo.getColaborador().getIdColaborador() != colaborador.getIdColaborador()) {
                    respuesta.setContenido("El Correo electrónico ya está registrado");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.update("colaboradores.editarColaborador", colaborador);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Colaborador editado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo editar al colaborador. Inténtelo nuevamente.");
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

    public static RespuestaColaboradores eliminarColaborador(int idColaborador) {
        RespuestaColaboradores respuesta = new RespuestaColaboradores();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("colaboradores.eliminarColaborador", idColaborador);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Colaborador eliminado exitosamente.");
                } else {
                    respuesta.setError(true);
                    respuesta.setContenido("No se pudo eliminar al colaborador. Inténtelo nuevamente.");
                }
                conexionBD.commit();
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static RespuestaColaboradores buscarColaboradores(String parametro) {
        RespuestaColaboradores respuesta = new RespuestaColaboradores();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Colaborador> colaboradores = conexionBD.selectList("colaboradores.buscarColaboradores", parametro);

                if (colaboradores != null && !colaboradores.isEmpty()) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setColaborador(colaboradores);
                } else {
                    respuesta.setError(true);
                    respuesta.setContenido("No se encontraron colaboradores con el criterio proporcionado.");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static RespuestaColaborador buscarColaboradoresExactos(String parametro) {
        RespuestaColaborador respuesta = new RespuestaColaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Colaborador colaborador = conexionBD.selectOne("colaboradores.buscarColaboradoresExactos", parametro);

                if (colaborador != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setColaborador(colaborador);
                } else {
                    respuesta.setContenido("No se encontraron colaboradores con el criterio proporcionado.");
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
}
