// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 17/11/2026
// Hora realizado: 9:47 pm
// Profesor: Julio Castro Ortiz

// Datos personales del empleado
public class InformacionPersonal {
    private String nombre;
    private String direccion;

    public InformacionPersonal(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}
