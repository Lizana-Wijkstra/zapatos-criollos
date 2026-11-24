// Nombre Autor: Lizana Alicia Wijkstra Santana 
// Matricula: 100702949
// Asignatura: INF514
// Sección: Z07
// Fecha realizado: 23/11/2026
// Hora realizado: 11:45 am
// Profesor: Julio Castro Ortiz

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

// App: arranca el servidor web y conecta las pantallas con las clases
public class App {

    // La nomina guarda los empleados y sus horas
    static Nomina nomina;

    public static void main(String[] args) {

        // Render crea la variable PORT. En tu computadora esa variable no existe
        int puerto = 7070;
        String puertoRender = System.getenv("PORT");

        if (puertoRender != null) {
            // En Render: sin MySQL, los datos se guardan en memoria
            puerto = Integer.parseInt(puertoRender);
            nomina = new NominaMemoria();
        } else {
            // En tu computadora: usa MySQL y pide la contraseña (no queda escrita en el código)
            System.out.print("Escribe la contraseña de MySQL y presiona Enter: ");
            ConexionMySQL.contrasena = new java.util.Scanner(System.in).nextLine();
            nomina = new Nomina();
        }

        // Crea el servidor. La página estructura.html está en la carpeta resources/templates
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinThymeleaf());
        });

        // Pantalla principal
        app.get("/", ctx -> {
            ctx.render("templates/estructura.html", crearModelo("principal"));
        });

        // Pantalla de captura de datos
        app.get("/captura", ctx -> {
            ctx.render("templates/estructura.html", crearModelo("captura"));
        });

        // Pantalla con la lista de empleados
        app.get("/lista", ctx -> {
            ctx.render("templates/estructura.html", crearModelo("lista"));
        });

        // Guardar un empleado
        app.post("/guardar-empleado", ctx -> {
            String numero = ctx.formParam("numeroEmpleado");
            String nombre = ctx.formParam("nombre");
            String direccion = ctx.formParam("direccion");
            String tasaTexto = ctx.formParam("tasaPago");
            String mensaje;

            if (numero == null || numero.isEmpty() || nombre == null || nombre.isEmpty()
                    || direccion == null || direccion.isEmpty() || tasaTexto == null || tasaTexto.isEmpty()) {
                mensaje = "Llena todos los campos.";
            } else if (nomina.buscarEmpleado(numero) != null) {
                mensaje = "Ya existe un empleado con ese número.";
            } else {
                try {
                    double tasa = Double.parseDouble(tasaTexto);
                    InformacionPersonal personal = new InformacionPersonal(nombre, direccion);
                    InformacionPago pago = new InformacionPago(tasa);
                    boolean guardado = nomina.agregarEmpleado(new Empleado(numero, personal, pago));
                    if (guardado) {
                        mensaje = "Empleado guardado.";
                    } else {
                        mensaje = "No se pudo guardar. Revisa la conexión con MySQL.";
                    }
                } catch (NumberFormatException e) {
                    mensaje = "La tasa de pago no es un número válido.";
                }
            }

            Map<String, Object> modelo = crearModelo("captura");
            modelo.put("mensajeEmpleado", mensaje);
            ctx.render("templates/estructura.html", modelo);
        });

        // Guardar las horas trabajadas
        app.post("/registrar-tiempo", ctx -> {
            String numero = ctx.formParam("numeroEmpleado");
            String horasTexto = ctx.formParam("horasTrabajadas");
            String mensaje;

            if (numero == null || numero.isEmpty() || horasTexto == null || horasTexto.isEmpty()) {
                mensaje = "Llena todos los campos.";
            } else {
                try {
                    double horas = Double.parseDouble(horasTexto);
                    boolean guardado = nomina.registrarHoras(numero, horas);
                    if (guardado) {
                        mensaje = "Horas guardadas.";
                    } else {
                        mensaje = "No existe un empleado con ese número.";
                    }
                } catch (NumberFormatException e) {
                    mensaje = "Las horas no son un número válido.";
                }
            }

            Map<String, Object> modelo = crearModelo("captura");
            modelo.put("mensajeHoras", mensaje);
            ctx.render("templates/estructura.html", modelo);
        });

        // Pantalla del cheque (si viene un número de empleado, genera el cheque)
        app.get("/cheque", ctx -> {
            String numero = ctx.queryParam("numero");
            Map<String, Object> modelo = crearModelo("cheque");

            if (numero != null && !numero.isEmpty()) {
                Cheque cheque = nomina.generarCheque(numero);
                if (cheque == null) {
                    modelo.put("mensajeCheque", "No existe el empleado o todavía no tiene horas registradas.");
                } else {
                    modelo.put("cheque", cheque);
                }
            }
            ctx.render("templates/estructura.html", modelo);
        });

        // Arranca el servidor
        app.start(puerto);
    }

    // Crea los datos que necesita la página: qué pantalla mostrar, los empleados y la fecha
    static Map<String, Object> crearModelo(String pantalla) {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("pantalla", pantalla);
        modelo.put("empleados", nomina.getEmpleados());
        modelo.put("fecha", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return modelo;
    }
}