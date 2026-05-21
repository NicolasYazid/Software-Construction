/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 7 de mayo del 2026
 */
package controlescolarfx.modelo.dao;

import controlescolarfx.modelo.ConexionBD;
import controlescolarfx.modelo.pojo.Sesion;
import controlescolarfx.modelo.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yazid
 */
public class AutenticacionDAO {

    //otro nombre credencialesValidas;
    public static Sesion validarSesionUsuario(String noPersonal,
            String contrasenia) throws SQLException, NullPointerException {
        
        Sesion sesion = new Sesion();
        Connection conexion = ConexionBD.obtenerConexion();

        if (conexion != null) {
            String sentencia = "SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno, noPersonal, tipoContratacion "
                    + "FROM usuario "
                    + "WHERE noPersonal = ? "
                    + "AND contrasenia = ?";
            PreparedStatement sentenciaBD = conexion.prepareStatement(sentencia);
            sentenciaBD.setString(1, noPersonal);
            sentenciaBD.setString(2, contrasenia);

            // ResultSet solo funciona con consultas/Querys (Paso 3)
            ResultSet resultado = sentenciaBD.executeQuery();
            // ResultSet es una matriz
            if (resultado.next()) {
                // Existen las credenciales

                int idUsuario = resultado.getInt("idUsuario");
                String nombre = resultado.getString("nombre");
                String apellidoPaterno = resultado.getString("apellidoPaterno");
                String apellidoMaterno = resultado.getString("apellidoMaterno");
                String noPersonal1 = resultado.getString("noPersonal");
                String tipoContratacion = resultado.getString("tipoContratacion");

                Usuario usuario = new Usuario(idUsuario, nombre, apellidoPaterno, apellidoMaterno, noPersonal1, "", tipoContratacion);
                sesion.setCredencialesEncontradas(true);
                sesion.setMensaje("Credenciales verificadas");
                sesion.setUsuarioSesion(usuario);
            } else {
                sesion.setCredencialesEncontradas(false);
                sesion.setMensaje("No. de personal y/o contraseña incorrectas, porfavor verifique");
            }
            resultado.close();
            conexion.close();
        } else {
            sesion.setCredencialesEncontradas(false);
            sesion.setMensaje("Lo sentimos, por el momento no se pueden verificar las credenciales, intentelo más tarde");
        }
        return sesion;

        //crear la base de datos control_escolar
        //crear la primera tabla usuarios (autoincremental)
    }

}
