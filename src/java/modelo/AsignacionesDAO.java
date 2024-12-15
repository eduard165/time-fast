package modelo;

import java.util.List;
import modelo.pojo.Asignacion;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.Unidad;
import modelo.pojo.respuestas.RespuestaAsignacion;
import modelo.pojo.respuestas.RespuestaAsignaciones;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AsignacionesDAO {

    public static Mensaje asignarVehiculoAConductor(Asignacion asignacion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                if (buscarAsignacionesPorConductor(asignacion.getIdColaborador()).getAsignacion() != null) {
                    respuesta.setContenido("El conductor ya tiene una unidad asignada.");
                    return respuesta;
                }

                if (verificarUnidad(asignacion.getIdUnidad())) {
                    respuesta.setContenido("La unidad no existe.");
                    return respuesta;
                }
                
                if(obtenerAsignacionPorUnidad(asignacion.getIdUnidad()) != null){
                    respuesta.setContenido("La unidad ya tiene asignado un conductor intente editar.");
                    return respuesta;
                }
                
                Mensaje rest = verificarColaborador(asignacion.getIdColaborador());
                if (rest.isError()) {
                    respuesta.setContenido(rest.getContenido());
                    return respuesta;
                }
                
                int filasAfectadas = conexionBD.insert("asignaciones.insertarAsignacion", asignacion);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Vehículo asignado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo asignar el vehículo. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setContenido("Error: " + e.toString());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: No se puede acceder a la base de datos.");
        }

        return respuesta;
    }

    public static Mensaje editarAsignacion(Asignacion asignacion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                RespuestaAsignacion verificadorConductor = buscarAsignacionesPorConductor(asignacion.getIdColaborador());
                if (verificadorConductor.getAsignacion() != null && verificadorConductor.getAsignacion().getIdAsignacion() != asignacion.getIdAsignacion()) {
                    respuesta.setContenido("El conductor ya tiene una unidad asignada.");
                    return respuesta;
                }

                if (verificarUnidad(asignacion.getIdUnidad())) {
                    respuesta.setContenido("La unidad no existe.");
                    return respuesta;
                }
               
                Mensaje rest = verificarColaborador(asignacion.getIdColaborador());
                if (rest.isError()) {
                    respuesta.setContenido(rest.getContenido());
                    return respuesta;
                }
                int filasAfectadas = conexionBD.update("asignaciones.actualizarAsignacion", asignacion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Asignación actualizada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo actualizar la asignación. Inténtelo nuevamente.");
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

    public static Mensaje eliminarAsignacion(int idAsignacion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("asignaciones.eliminarAsignacion", idAsignacion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Asignación eliminada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar la asignación. Inténtelo nuevamente.");
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

    public static RespuestaAsignaciones obtenerAsignaciones() {
        RespuestaAsignaciones respuesta = new RespuestaAsignaciones();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                List<Asignacion> asignaciones = conexionBD.selectList("asignaciones.obtenerAsignaciones");

                if (asignaciones != null && !asignaciones.isEmpty()) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setAsignaciones(asignaciones);
                } else {
                    respuesta.setContenido("No se encontraron asignaciones.");
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

    public static RespuestaAsignacion obtenerAsignacionPorId(int idAsignacion) {
        RespuestaAsignacion respuesta = new RespuestaAsignacion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Asignacion asignacion = conexionBD.selectOne("asignaciones.obtenerAsignacionPorId", idAsignacion);

                if (asignacion != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setAsignacion(asignacion);
                } else {
                    respuesta.setContenido("No se encontró la asignación.");
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

    public static RespuestaAsignacion buscarAsignacionesPorConductor(int idConductor) {
        RespuestaAsignacion respuesta = new RespuestaAsignacion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Asignacion asignacion = conexionBD.selectOne("asignaciones.buscarAsignacionesPorConductor", idConductor);

                if (asignacion != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setAsignacion(asignacion);
                } else {
                    respuesta.setContenido("No se encontraron asignaciones para este conductor.");
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

    public static Mensaje verificarColaborador(int idColaborador) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        Mensaje rest = new Mensaje();
        rest.setError(true);

        if (conexionBD != null) {
            try {
                Colaborador colaborador = conexionBD.selectOne("colaboradores.obtenerColaboradorPorId", idColaborador);
                if (colaborador == null) {
                    rest.setContenido("El colaborador no existe");
                    return rest;
                } else {
                    if (colaborador.getIdRol() != 3) {
                        rest.setContenido("El colaborador no es un conductor.");
                        return rest;
                    }
                    rest.setError(false);
                    rest.setContenido("TODO BIEN");
                    return rest;
                }
            } finally {
                conexionBD.close();
            }
        }
        return rest;
    }

    public static boolean verificarUnidad(int idUnidad) {
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                Unidad unidad = conexionBD.selectOne("unidades.obtenerUnidadPorId", idUnidad);

                if (unidad == null) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                conexionBD.close();
            }
        }
        return false;
    }

    public static Asignacion obtenerAsignacionPorUnidad(int idUnidad) {
        Asignacion respuesta = new Asignacion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                 respuesta = conexionBD.selectOne("asignaciones.buscarAsignacionesPorIdUnidad", idUnidad);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } 
        return respuesta;
    }
}
