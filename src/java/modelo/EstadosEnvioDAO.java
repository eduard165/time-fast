/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.pojo.EstadoEnvio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EstadosEnvioDAO {
     public static List<EstadoEnvio> consultarEnviosAsignados() {
        SqlSession conexionBD = MyBatisUtil.getSession();
        List<EstadoEnvio> estados = new ArrayList<>();

        if (conexionBD != null) {
            try {
                estados = conexionBD.selectList("estados.obtenerEstados");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } 
        return estados;
    }
}
