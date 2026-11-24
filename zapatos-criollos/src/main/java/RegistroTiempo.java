// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 17/11/2026
// Hora realizado: 9:55 pm
// Profesor: Julio Castro Ortiz

// Registro del archivo de tiempo: número de empleado, nombre y horas trabajadas
public class RegistroTiempo {
    private String numero;
    private String nombre;
    private double horas;

    public RegistroTiempo(String numero, String nombre, double horas) {
        this.numero = numero;
        this.nombre = nombre;
        this.horas = horas;
    }

    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }
}
