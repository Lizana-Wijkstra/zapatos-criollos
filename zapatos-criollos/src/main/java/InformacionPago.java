// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 17/11/2026
// Hora realizado: 9:41 pm
// Profesor: Julio Castro Ortiz

// Información de pago del empleado (la tasa por hora)
public class InformacionPago {
    private double tasaPago;

    public InformacionPago(double tasaPago) {
        this.tasaPago = tasaPago;
    }

    public double getTasaPago() {
        return tasaPago;
    }

    // Información de pago = Tasa de pago * Horas trabajadas
    public double calcular(double horas) {
        return tasaPago * horas;
    }
}
