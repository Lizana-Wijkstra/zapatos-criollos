// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 21/11/2026
// Hora realizado: 8:49 pm
// Profesor: Julio Castro Ortiz

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase para conectarse a MySQL
public class ConexionMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/zapatos_criollos";
    private static final String USUARIO = "root";
    public static String contrasena = "";

    // Devuelve una conexión a la base de datos
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, contrasena);
    }
}