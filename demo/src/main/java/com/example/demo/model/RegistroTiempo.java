// Nombre del Autor: [Tu Nombre]
// Matrícula: [Tu Matrícula]
// Asignatura: Programación
// Sección: [Tu Sección]
// Fecha de Entrega: [Fecha]
// Hora de Entrega: [Hora]
// Profesor: [Nombre del Profesor]

package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "archivo_tiempo")
public class RegistroTiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroEmpleado;
    private String nombreEmpleado;
    private double horasTrabajadas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }
    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }
    public double getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(double horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }
}
