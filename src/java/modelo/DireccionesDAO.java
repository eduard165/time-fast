/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.pojo.Direccion;
import modelo.pojo.Mensaje;
import modelo.pojo.respuestas.RespuestaDireccion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author eduar
 */
public class DireccionesDAO {
    public static Mensaje registrarDireccion(Direccion direccion) {
    Mensaje respuesta = new Mensaje();
    SqlSession conexionBD = MyBatisUtil.getSession();
    respuesta.setError(true);

    if (conexionBD != null) {
        try {
            int filasAfectadas = conexionBD.insert("direcciones.insertarDireccion", direccion);

            if (filasAfectadas > 0) {
                respuesta.setError(false);
                respuesta.setContenido("Dirección registrada exitosamente");
            } else {
                respuesta.setContenido("No se pudo registrar la dirección. Inténtelo nuevamente.");
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


    public static Mensaje editarDireccion(Direccion direccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("direcciones.editarDireccion", direccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección editada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo editar la dirección. Inténtelo nuevamente.");
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
    public static Mensaje eliminarDireccion(int idDireccion) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("direcciones.eliminarDireccion", idDireccion);

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("Dirección eliminada exitosamente.");
                } else {
                    respuesta.setContenido("No se pudo eliminar la dirección. Inténtelo nuevamente.");
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


    public static RespuestaDireccion buscarDireccion(int idDireccion) {
        RespuestaDireccion respuesta = new RespuestaDireccion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuesta.setError(true);

        if (conexionBD != null) {
            try {
                Direccion direccion = conexionBD.selectOne("direcciones.buscarDireccionPorId", idDireccion);

                if (direccion != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Búsqueda exitosa.");
                    respuesta.setDireccion(direccion);
                } else {
                    respuesta.setContenido("No se encontró la dirección con el ID proporcionado.");
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
