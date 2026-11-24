// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 21/11/2026
// Hora realizado: 11:09 am
// Profesor: Julio Castro Ortiz


// Cheque de pago: número, nombre, dirección y cantidades del pago actual
public class Cheque {
    private String numeroEmpleado;
    private String nombre;
    private String direccion;
    private PagoActual pagoActual;

    public Cheque(String numeroEmpleado, String nombre, String direccion, PagoActual pagoActual) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.pagoActual = pagoActual;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public PagoActual getPagoActual() {
        return pagoActual;
    }
}
