// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 21/11/2026
// Hora realizado: 11:11 pm
// Profesor: Julio Castro Ortiz

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Nomina: guarda los datos en MySQL y crea el cheque
public class Nomina {

    // Guarda un empleado
    public boolean agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (numero, nombre, direccion, tasa_pago) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionMySQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empleado.getNumero());
            ps.setString(2, empleado.getPersonal().getNombre());
            ps.setString(3, empleado.getPersonal().getDireccion());
            ps.setDouble(4, empleado.getPago().getTasaPago());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Busca un empleado por su número (si no existe devuelve null)
    public Empleado buscarEmpleado(String numero) {
        String sql = "SELECT * FROM empleado WHERE numero = ?";
        try (Connection con = ConexionMySQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InformacionPersonal personal = new InformacionPersonal(rs.getString("nombre"), rs.getString("direccion"));
                InformacionPago pago = new InformacionPago(rs.getDouble("tasa_pago"));
                return new Empleado(numero, personal, pago);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    // Guarda las horas de un empleado
    public boolean registrarHoras(String numero, double horas) {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado == null) {
            return false;
        }
        String sql = "INSERT INTO registro_tiempo (numero, nombre, horas) VALUES (?, ?, ?)";
        try (Connection con = ConexionMySQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);
            ps.setString(2, empleado.getPersonal().getNombre());
            ps.setDouble(3, horas);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Crea el cheque (si falta el empleado o sus horas devuelve null)
    public Cheque generarCheque(String numero) {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado == null) {
            return null;
        }

        // Busca las últimas horas del empleado
        double horas = -1;
        String sql = "SELECT horas FROM registro_tiempo WHERE numero = ? ORDER BY id DESC LIMIT 1";
        try (Connection con = ConexionMySQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                horas = rs.getDouble("horas");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (horas == -1) {
            return null;
        }

        // Pago bruto = tasa * horas
        double pagoBruto = empleado.getPago().calcular(horas);

        // Pago actual = pago bruto - (impuesto + seguro)
        PagoActual pagoActual = new PagoActual(pagoBruto);
        empleado.setPagoActual(pagoActual);

        String nombre = empleado.getPersonal().getNombre();
        String direccion = empleado.getPersonal().getDireccion();
        return new Cheque(numero, nombre, direccion, pagoActual);
    }

    // Devuelve todos los empleados
    public ArrayList<Empleado> getEmpleados() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado ORDER BY numero";
        try (Connection con = ConexionMySQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InformacionPersonal personal = new InformacionPersonal(rs.getString("nombre"), rs.getString("direccion"));
                InformacionPago pago = new InformacionPago(rs.getDouble("tasa_pago"));
                lista.add(new Empleado(rs.getString("numero"), personal, pago));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}