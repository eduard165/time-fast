package modelo;

import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.Asignacion;
import modelo.pojo.Mensaje;
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
                verificarAsignacion(asignacion);
                int filasAfectadas = conexionBD.insert("asignaciones.registrarAsignacion", asignacion);
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Vehículo asignado exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo asignar el vehículo. Inténtelo nuevamente.");
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

    public static Mensaje editarAsignacion(Asignacion asignacion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                verificarAsignacion(asignacion);
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

    public static boolean verificarAsignacion(Asignacion asignacion) {
        RespuestaAsignacion respuestaAsignacion = buscarAsignacionesPorConductor(asignacion.getIdAsignacion());
        if (!respuestaAsignacion.isError() && respuestaAsignacion.getAsignacion() != null) {
            return true;
        }
        return false;

    }
}
