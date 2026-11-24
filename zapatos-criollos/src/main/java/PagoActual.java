// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 18/11/2026
// Hora realizado: 9:03 pm
// Profesor: Julio Castro Ortiz

// Pago actual: pago bruto, descuentos y pago neto
public class PagoActual {
    // Porcentajes que yo supuse porque el enunciado no los da
    private static final double PORCENTAJE_ISR = 0.10;
    private static final double PORCENTAJE_SEGURO = 0.0304;

    private double pagoBruto;
    private double impuestoRenta;
    private double seguroMedico;

    // El pago bruto es lo que sale de la Información de pago
    public PagoActual(double pagoBruto) {
        this.pagoBruto = pagoBruto;
        this.impuestoRenta = pagoBruto * PORCENTAJE_ISR;
        this.seguroMedico = pagoBruto * PORCENTAJE_SEGURO;
    }

    public double getPagoBruto() {
        return pagoBruto;
    }

    public double getImpuestoRenta() {
        return impuestoRenta;
    }

    public double getSeguroMedico() {
        return seguroMedico;
    }

    // Pago actual = Pago bruto - (Impuesto sobre la Renta + Seguro Médico)
    public double getPagoNeto() {
        return pagoBruto - (impuestoRenta + seguroMedico);
    }
}
