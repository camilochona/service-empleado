package com.parameta.empleado.controller;

import com.parameta.empleado.entity.Empleado;
import com.parameta.empleado.soap.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@PostMapping
	public Empleado registrarEmpleado(@Valid @RequestBody Empleado empleado) {
		
		// Calcular edad
		LocalDate fechaNac = empleado.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate hoy = LocalDate.now();
		Period edad = Period.between(fechaNac, hoy);
		
		if (edad.getYears() < 18) {
			throw new IllegalArgumentException("El empleado debe ser mayor de edad.");
		}
		
        if (empleado.getFechaNacimiento() == null || empleado.getFechaVinculacion() == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias.");
        }
        
        if (empleado.getSalario() <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor a cero.");
        }
        
        if (empleado.getFechaVinculacion().before(empleado.getFechaNacimiento())) {
            throw new IllegalArgumentException("La fecha de vinculación no puede ser anterior a la fecha de nacimiento.");
        }
         
		// Calcular tiempo de vinculación
		LocalDate fechaVinc = empleado.getFechaVinculacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period tiempoVinculacion = Period.between(fechaVinc, hoy);

		// Guardar mediante el servicio SOAP
		empleadoService.guardarEmpleado(empleado);

		// Agregar edad y tiempo de vinculación como campos adicionales
		empleado.setEdadFormateada(formatearPeriodo(edad));
		empleado.setTiempoVinculacionFormateado(formatearPeriodo(tiempoVinculacion));

		return empleado;
	}

	private String formatearPeriodo(Period p) {
		return p.getYears() + " años, " + p.getMonths() + " meses, " + p.getDays() + " días";
	}
}
