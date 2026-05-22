/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 14 de mayo del 2026
 */
package controlescolarfx.modelo.dao;

import controlescolarfx.modelo.ConexionBD;
import controlescolarfx.modelo.pojo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazid
 */
public class AlumnoDAO {

    public static List<Alumno> obtenerAlumnos() throws SQLException, NullPointerException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            String consulta = "SELECT a.idAlumno, a.nombre, a.apellidoPaterno, a.apellidoMaterno, "
                + "a.matricula, a.email, a.idCarrera, c.nombre, "
                + "c.idFacultad, f.nombre "
                + "FROM alumno a "
                + "INNER JOIN carrera c ON c.idCarrera = a.idCarrera "
                + "INNER JOIN facultad f ON f.idFacultad = c.idFacultad";
            
            PreparedStatement sentencia = conexionBD.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Alumno alumno = serializarAlumno(resultado);
                alumnos.add(alumno);
            }
            conexionBD.close();
            return alumnos;
        }
        throw new SQLException("No hay conexión con la base de datos/almacenamiento de información.");
    }
    
    private static Alumno serializarAlumno(ResultSet resultado) throws NullPointerException, SQLException {
            Alumno alumno = new Alumno();
            alumno.setIdAlumno(resultado.getInt("idAlumno"));
            alumno.setNombre(resultado.getString("nombre"));
            alumno.setApellidoPaterno(resultado.getString("apellidoPaterno"));
            alumno.setApellidoMaterno(
                    (resultado.getString("apellidoMaterno") != null) ? resultado.getString("apellidoMaterno"): "");
            alumno.setMatricula(resultado.getString("matricula"));
            alumno.setEmail(resultado.getString("email"));
            alumno.setIdCarrera(resultado.getInt("idCarrera"));
            alumno.setCarrera(resultado.getString("nombre"));
            alumno.setIdFacultad(resultado.getInt("idFacultad"));
            alumno.setFacultad(resultado.getString("nombre"));
            return alumno;
        }
}
