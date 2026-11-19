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
@Table(name = "empleados")
public class Empleado {

    @Id
    private String numeroEmpleado;
    private String nombre;
    private String direccion;
    private double tasaPago;
    private double informacionPago;
    private double informacionPagoActual;

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public double getTasaPago() { return tasaPago; }
    public void setTasaPago(double tasaPago) { this.tasaPago = tasaPago; }
    public double getInformacionPago() { return informacionPago; }
    public void setInformacionPago(double informacionPago) { this.informacionPago = informacionPago; }
    public double getInformacionPagoActual() { return informacionPagoActual; }
    public void setInformacionPagoActual(double informacionPagoActual) { this.informacionPagoActual = informacionPagoActual; }
}
