// Nombre del Autor: [Tu Nombre]
// Matrícula: [Tu Matrícula]
// Asignatura: Programación
// Sección: [Tu Sección]
// Fecha de Entrega: [Fecha]
// Hora de Entrega: [Hora]
// Profesor: [Nombre del Profesor]

package com.example.demo.controller;

import com.example.demo.model.Empleado;
import com.example.demo.model.RegistroTiempo;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.RegistroTiempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NominaController {

    @Autowired
    private EmpleadoRepository empleadoRepo;

    @Autowired
    private RegistroTiempoRepository tiempoRepo;

    private static final double TASA_ISR = 0.10;
    private static final double TASA_SEGURO_MEDICO = 0.0304;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("empleados", empleadoRepo.findAll());
        model.addAttribute("tiempos", tiempoRepo.findAll());
        return "index";
    }

    @PostMapping("/guardar-empleado")
    public String guardarEmpleado(@RequestParam String numeroEmpleado,
                                  @RequestParam String nombre,
                                  @RequestParam String direccion,
                                  @RequestParam double tasaPago) {
        Empleado emp = empleadoRepo.findById(numeroEmpleado).orElse(new Empleado());
        emp.setNumeroEmpleado(numeroEmpleado);
        emp.setNombre(nombre);
        emp.setDireccion(direccion);
        emp.setTasaPago(tasaPago);
        empleadoRepo.save(emp);
        return "redirect:/";
    }

    @PostMapping("/registrar-tiempo")
    public String registrarTiempo(@RequestParam String numeroEmpleado,
                                  @RequestParam double horasTrabajadas) {
        Empleado emp = empleadoRepo.findById(numeroEmpleado).orElse(null);
        if (emp != null) {
            RegistroTiempo tiempo = new RegistroTiempo();
            tiempo.setNumeroEmpleado(emp.getNumeroEmpleado());
            tiempo.setNombreEmpleado(emp.getNombre());
            tiempo.setHorasTrabajadas(horasTrabajadas);
            tiempoRepo.save(tiempo);

            double pagoBruto = emp.getTasaPago() * horasTrabajadas;
            double isr = pagoBruto * TASA_ISR;
            double seguroMedico = pagoBruto * TASA_SEGURO_MEDICO;
            double pagoActual = pagoBruto - (isr + seguroMedico);

            emp.setInformacionPago(pagoBruto);
            emp.setInformacionPagoActual(pagoActual);
            empleadoRepo.save(emp);
        }
        return "redirect:/";
    }

    @GetMapping("/cheque/{id}")
    public String verCheque(@PathVariable String id, Model model) {
        Empleado emp = empleadoRepo.findById(id).orElse(null);
        model.addAttribute("cheque", emp);
        model.addAttribute("empleados", empleadoRepo.findAll());
        model.addAttribute("tiempos", tiempoRepo.findAll());
        return "index";
    }
}
