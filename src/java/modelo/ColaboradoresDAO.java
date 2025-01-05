package modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaColaborador;
import modelo.pojo.respuestas.RespuestaColaboradores;
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

    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
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

    public static Mensaje eliminarColaborador(int idColaborador) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                conexionBD.commit(false);

                int filasAfectadasColaborador = conexionBD.delete("colaboradores.borrarColaborador", idColaborador);

                if (filasAfectadasColaborador > 0) {
                    conexionBD.commit();
                    respuesta.setContenido("Colaborador eliminado exitosamente.");
                    respuesta.setError(false);
                } else {
                    conexionBD.rollback();
                    respuesta.setContenido("No se pudo eliminar el colaborador. Inténtelo nuevamente.");
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

    public static Mensaje desactivarColaborador(int idColaborador) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadasColaborador = conexionBD.update("colaboradores.desactivarColaborador", idColaborador);

                if (filasAfectadasColaborador > 0) {
                    respuesta.setContenido("Colaborador desactivado exitosamente.");
                    respuesta.setError(false);
                } else {
                    conexionBD.rollback();
                    respuesta.setContenido("No se pudo desactivar el colaborador.\n Inténtelo nuevamente." );
                }
                conexionBD.commit();

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

    public static RespuestaColaboradores buscarColaboradores(String parametro) {
        RespuestaColaboradores respuesta = new RespuestaColaboradores();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                List<Colaborador> colaboradores = conexionBD.selectList("colaboradores.buscarColaboradores", parametro);

                if (colaboradores != null && !colaboradores.isEmpty()) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setColaborador(colaboradores);
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

    public static List<Colaborador> listaColaboradoresActivos() {
        List<Colaborador> colaboradores = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaboradores.obtenerTodosActivos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return colaboradores;
    }

    public static List<Colaborador> listaColaboradoresInactivos() {
        List<Colaborador> colaboradores = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaboradores.obtenerTodosInactivos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return colaboradores;
    }

    public static List<Colaborador> listaConductores() {
        List<Colaborador> colaboradores = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaboradores.obtenerConductores", 3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return colaboradores;
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

    public static Mensaje subirFoto(Integer idColaborador, byte[] fotografia) {
        Mensaje msj = new Mensaje();
        msj.setError(true);

        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idColaborador", idColaborador);
        parametros.put("fotografia", fotografia);
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("colaboradores.guardarFoto", parametros);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setContenido("Foto del colaborador actualizada con exito");
                } else {
                    msj.setContenido("La foto del colaborador no pudo ser guardad cone exito");
                }
            } catch (Exception e) {
                msj.setContenido(e.getMessage());
            }
        } else {
            msj.setContenido("Por el momento no hay conexion estable reinicie la aplicacion nuevamente");
        }
        return msj;
    }

    public static Mensaje subirFotoPorNumeroPersonal(String numeroPersonal, byte[] fotografia) {
        Mensaje msj = new Mensaje();
        msj.setError(true);

        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("numeroPersonal", numeroPersonal);
        parametros.put("fotografia", fotografia);
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("colaboradores.guardarFotoPorNumeroPersonal", parametros);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setContenido("Foto del colaborador actualizada con exito");
                } else {
                    msj.setContenido("La foto del colaborador no pudo ser guardad cone exito");
                }
            } catch (Exception e) {
                msj.setContenido(e.getMessage());
            }
        } else {
            msj.setContenido("Por el momento no hay conexion estable reinicie la aplicacion nuevamente");
        }
        return msj;
    }

    public static Colaborador obtenerFoto(Integer idColaborador) {
        Colaborador colaboradorConFoto = null;
        SqlSession conexionBd = MyBatisUtil.getSession();
        if (conexionBd != null) {
            try {
                colaboradorConFoto = conexionBd.selectOne("colaboradores.obtenerFoto", idColaborador);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradorConFoto;
    }

    public static boolean verificarColaboradorPorId(int idColaborador) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                Colaborador colaborador = conexionBD.selectOne("colaboradores.obtenerColaboradorPorId", idColaborador);
                if (colaborador != null) {
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

    public static Colaborador buscarColaboradorPorId(int idColaborador) {
        Colaborador colaborador = new Colaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        boolean existe = false;

        if (conexionBD != null) {
            try {
                colaborador = conexionBD.selectOne("colaboradores.obtenerColaboradorPorId", idColaborador);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return colaborador;
    }
}
