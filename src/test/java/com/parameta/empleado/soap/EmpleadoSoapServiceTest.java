package com.parameta.empleado.soap;

import com.parameta.empleado.entity.Empleado;
import com.parameta.empleado.repository.EmpleadoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EmpleadoSoapServiceTest {

    private EmpleadoRepository mockRepository;
    private EmpleadoServiceImpl empleadoService;
    
    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(EmpleadoRepository.class);
        empleadoService = new EmpleadoServiceImpl(mockRepository);
    }

    @Test
    void testGuardarEmpleado_Valido_NoLanzaExcepcion() {
        Empleado empleado = new Empleado();
        empleado.setNombres("Juan");
        empleado.setApellidos("Pérez");
        empleado.setTipoDocumento("CC");
        empleado.setNumeroDocumento("123456789");
        empleado.setCargo("Desarrollador");
        empleado.setSalario(5000000.0);
        empleado.setFechaNacimiento(new java.util.Date(90, 1, 1)); // 1990-02-01
        empleado.setFechaVinculacion(new java.util.Date(120, 0, 1)); // 2020-01-01

        assertDoesNotThrow(() -> empleadoService.guardarEmpleado(empleado));
    }
}
