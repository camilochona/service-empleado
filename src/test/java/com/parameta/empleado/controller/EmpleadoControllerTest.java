package com.parameta.empleado.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.parameta.empleado.entity.Empleado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Caso de éxito
    @Test
    void registrarEmpleado_datosValidos_devuelveEmpleadoConEdadYVinculacion() throws Exception {
        Empleado empleado = crearEmpleadoValido();
        
        mockMvc.perform(post("/api/empleado")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(empleado)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombres").value("Juan"))
               .andExpect(jsonPath("$.edadFormateada").exists())
               .andExpect(jsonPath("$.tiempoVinculacionFormateado").exists());
    }

    // Casos de validación
    @Test
    void registrarEmpleado_menorEdad_devuelveBadRequest() throws Exception {
        Empleado empleado = crearEmpleadoValido();
        empleado.setFechaNacimiento(sdf.parse("2010-01-01")); // 13 años
        
        mockMvc.perform(post("/api/empleado")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(empleado)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.error").value("El empleado debe ser mayor de edad."));
    }

    @Test
    void registrarEmpleado_fechaVinculacionAnteriorANacimiento_devuelveBadRequest() throws Exception {
        Empleado empleado = crearEmpleadoValido();
        empleado.setFechaNacimiento(sdf.parse("1990-01-01"));
        empleado.setFechaVinculacion(sdf.parse("1989-01-01")); // Fecha anterior
        
        mockMvc.perform(post("/api/empleado")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(empleado)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.error").value("La fecha de vinculación no puede ser anterior a la fecha de nacimiento."));
    }

    @Test
    void registrarEmpleado_salarioCero_devuelveBadRequest() throws Exception {
        Empleado empleado = crearEmpleadoValido();
        empleado.setSalario(0.0);
        
        mockMvc.perform(post("/api/empleado")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(empleado)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.error").value("El salario debe ser mayor a cero."));
    }


    // Casos específicos para campos string
    @Test
    void registrarEmpleado_nombreVacio_devuelveBadRequest() throws Exception {
        Empleado empleado = crearEmpleadoValido();
        empleado.setNombres("");
        
        mockMvc.perform(post("/api/empleado")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(empleado)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.error").value("El nombre es obligatorio"));
    }

    @Test
    void registrarEmpleado_tipoDocumentoNull_devuelveBadRequest() throws Exception {
        Empleado empleado = crearEmpleadoValido();
        empleado.setTipoDocumento(null);
        
        mockMvc.perform(post("/api/empleado")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(empleado)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.error").value("El tipo de documento es obligatorio"));
    }

    // Método auxiliar para crear empleado válido
    private Empleado crearEmpleadoValido() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidos("Perez");
        empleado.setTipoDocumento("CC");
        empleado.setNumeroDocumento("123456789");
        empleado.setFechaNacimiento(sdf.parse("1990-01-01"));
        empleado.setFechaVinculacion(sdf.parse("2020-01-01"));
        empleado.setCargo("Desarrollador");
        empleado.setSalario(3000.0);
        return empleado;
    }
}