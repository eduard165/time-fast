/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import modelo.pojo.Cliente;
import modelo.pojo.Colaborador;
import modelo.pojo.RespuestaCliente;
import modelo.pojo.RespuestaColaborador;
import modelo.pojo.RespuestaColaboradores;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author eduar
 */
public class AutenticacionDAO {

    public static RespuestaCliente verificarSesionCliente(String correo, String password) {
        RespuestaCliente respuesta = new RespuestaCliente();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("correo", correo);
                parametros.put("password", password);
                Cliente clienteRest = conexionBD.selectOne("autenticacion.verificarCredencialesCliente", parametros);

                if (clienteRest != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Bienvenid(@) " + clienteRest.getNombre() + " al sistema.");
                    respuesta.setCliente(clienteRest);
                } else {
                    respuesta.setContenido("Correo y/o contraseña incorrectos, favor de verificar.");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }

        } else {
            respuesta.setContenido("Error: Por el momento no se puede acceder a la informacion.");
        }

        return respuesta;
    }

    public static RespuestaColaborador verificarSesionColaborador(String numeroPersonal, String password) {
        RespuestaColaborador respuesta = new RespuestaColaborador();
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("numeroPersonal", numeroPersonal);
                parametros.put("password", password);
                Colaborador colaborador = conexionDB.selectOne("autenticacion.verificarCredencialesColaborador", parametros);

                if (colaborador != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Bienvenid(@) " + colaborador.getNombre() + " al sistema.");
                    respuesta.setColaborador(colaborador);
                } else {
                    respuesta.setError(true);
                    respuesta.setContenido("Correo y/o contraseña incorrectos, favor de verificar.");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setContenido(e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setContenido("Error: Por el momento no se puede acceder a la informacion.");
        }
        return respuesta;
    }

}
