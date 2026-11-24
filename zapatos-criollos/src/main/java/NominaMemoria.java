// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 21/11/2026
// Hora realizado: 11:51 pm
// Profesor: Julio Castro Ortiz

import java.util.ArrayList;

// NominaMemoria: igual que Nomina, pero guarda los datos en listas (se usa en Render)
public class NominaMemoria extends Nomina {

    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<RegistroTiempo> registros = new ArrayList<>();

    // Guarda un empleado
    @Override
    public boolean agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        return true;
    }

    // Busca un empleado por su número
    @Override
    public Empleado buscarEmpleado(String numero) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNumero().equals(numero)) {
                return empleados.get(i);
            }
        }
        return null;
    }

    // Guarda las horas de un empleado
    @Override
    public boolean registrarHoras(String numero, double horas) {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado == null) {
            return false;
        }
        String nombre = empleado.getPersonal().getNombre();
        registros.add(new RegistroTiempo(numero, nombre, horas));
        return true;
    }

    // Crea el cheque
    @Override
    public Cheque generarCheque(String numero) {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado == null) {
            return null;
        }

        // Busca las horas del empleado
        double horas = -1;
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getNumero().equals(numero)) {
                horas = registros.get(i).getHoras();
            }
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
    @Override
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}