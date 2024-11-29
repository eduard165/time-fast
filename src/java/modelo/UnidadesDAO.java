/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Unidad;
import modelo.pojo.respuestas.RespuestaUnidad;
import modelo.pojo.respuestas.RespuestaUnidades;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UnidadesDAO {

    public static Mensaje registrarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                if (!buscarUnidadPorVin(unidad.getVIN()).isError()) {
                    respuesta.setContenido("El VIN ya está registrado.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("unidades.registrarUnidad", unidad);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Unidad registrada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo registrar la unidad. Inténtelo nuevamente.");
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

    public static Mensaje editarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                RespuestaUnidad vin = buscarUnidadPorVin(unidad.getVIN());

                if (!vin.isError() && vin.getUnidad().getIdUnidad() != unidad.getIdUnidad()) {
                    respuesta.setContenido("El VIN ya está registrado.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.update("unidades.editarUnidad", unidad);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Unidad editada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo editar la unidad. Inténtelo nuevamente.");
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

    public static Mensaje eliminarUnidad(int idUnidad) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("unidades.eliminarUnidad", idUnidad);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Unidad eliminada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar la unidad. Inténtelo nuevamente.");
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

    public static RespuestaUnidades buscarUnidades(String parametro) {
        RespuestaUnidades respuesta = new RespuestaUnidades();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                List<Unidad> unidades = conexionBD.selectList("unidades.buscarUnidades", parametro);

                if (unidades != null && !unidades.isEmpty()) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setUnidades(unidades);
                } else {
                    respuesta.setContenido("No se encontraron unidades con el criterio proporcionado.");
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

    public static RespuestaUnidad buscarUnidadPorVin(String vin) {
        RespuestaUnidad respuesta = new RespuestaUnidad();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Unidad unidad = conexionBD.selectOne("unidades.buscarUnidadPorVin", vin);

                if (unidad != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setUnidad(unidad);
                } else {
                    respuesta.setContenido("No se encontraron unidades con el VIN proporcionado.");
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
