/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 5 de mayo del 2026
 */
package controlescolarfx.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * Modelo: 
 * Vista: 
 * Controlador: 
 * 
 * @author yazid
 */
public class ConexionBD {
    
    // Estos datos podrían cambiarse para que permanezcan ocultos
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String NOMBRE_BD = "control_escolar";
    private static final String IP = "localhost"; // es lo mismo que 127.0.0.1
    private static final String PUERTO = "3306";
    private static final String USUARIO_BD = "root";
    private static final String PASSWORD = "root";

    //private static final String URL_CONEXION = "";
    public static Connection obtenerConexion() throws SQLException {
        try {
            String URL_CONEXION = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + NOMBRE_BD
                    + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

            Class.forName(DRIVER);

            return DriverManager.getConnection(URL_CONEXION, USUARIO_BD, PASSWORD); //IP
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontro el driver de MySQL: " + ex.getMessage(), ex);
        }
    }
}
