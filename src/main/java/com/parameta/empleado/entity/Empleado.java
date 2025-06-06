package com.parameta.empleado.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private Date fechaNacimiento;

    @NotNull(message = "La fecha de vinculación es obligatoria")
    private Date fechaVinculacion;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor a 0")
    private Double salario;
	
	@Transient
	private String edadFormateada;

	@Transient
	private String tiempoVinculacionFormateado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date  getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date  fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date  getFechaVinculacion() {
		return fechaVinculacion;
	}

	public void setFechaVinculacion(Date  fechaVinculacion) {
		this.fechaVinculacion = fechaVinculacion;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getEdadFormateada() {
		return edadFormateada;
	}

	public void setEdadFormateada(String edadFormateada) {
		this.edadFormateada = edadFormateada;
	}

	public String getTiempoVinculacionFormateado() {
		return tiempoVinculacionFormateado;
	}

	public void setTiempoVinculacionFormateado(String tiempoVinculacionFormateado) {
		this.tiempoVinculacionFormateado = tiempoVinculacionFormateado;
	}

	
}
