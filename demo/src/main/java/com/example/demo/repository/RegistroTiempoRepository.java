// Nombre del Autor: [Tu Nombre]
// Matrícula: [Tu Matrícula]
// Asignatura: Programación
// Sección: [Tu Sección]
// Fecha de Entrega: [Fecha]
// Hora de Entrega: [Hora]
// Profesor: [Nombre del Profesor]

package com.example.demo.repository;

import com.example.demo.model.RegistroTiempo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroTiempoRepository extends JpaRepository<RegistroTiempo, Long> {
}
