// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 24/11/2026
// Hora realizado: 4:33 pm
// Profesor: Julio Castro Ortiz

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase para conectarse a Neon (PostgreSQL) usando variables de entorno
public class ConexionNeon {

    public static Connection conectar() throws SQLException {
        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String contrasena = System.getenv("DB_PASSWORD");
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}