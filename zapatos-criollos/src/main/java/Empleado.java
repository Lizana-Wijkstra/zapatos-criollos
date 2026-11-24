// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 17/11/2026
// Hora realizado: 9:30 pm
// Profesor: Julio Castro Ortiz

// Registro de empleado: número, información personal, información de pago y información de pago actual
public class Empleado {
    private String numero;
    private InformacionPersonal personal;
    private InformacionPago pago;
    private PagoActual pagoActual;

    public Empleado(String numero, InformacionPersonal personal, InformacionPago pago) {
        this.numero = numero;
        this.personal = personal;
        this.pago = pago;
    }

    public String getNumero() {
        return numero;
    }

    public InformacionPersonal getPersonal() {
        return personal;
    }

    public InformacionPago getPago() {
        return pago;
    }

    public PagoActual getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(PagoActual pagoActual) {
        this.pagoActual = pagoActual;
    }
}