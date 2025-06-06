package com.parameta.empleado.service;

import com.parameta.empleado.entity.Empleado;
import com.parameta.empleado.repository.EmpleadoRepository;
import com.parameta.empleado.soap.EmpleadoServiceImpl;
import com.parameta.empleado.soap.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class EmpleadoServiceImplTest {

	private EmpleadoRepository mockRepository;
    private EmpleadoServiceImpl empleadoService;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(EmpleadoRepository.class);
        empleadoService = new EmpleadoServiceImpl(mockRepository);
    }

    @Test
    void testGuardarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombres("Laura");
        empleado.setApellidos("Martínez");
        empleado.setFechaNacimiento(new Date());
        empleado.setFechaVinculacion(new Date());
        empleado.setCargo("Analista");
        empleado.setNumeroDocumento("987654321");
        empleado.setTipoDocumento("CC");
        empleado.setSalario(4000000.0);

        assertDoesNotThrow(() -> empleadoService.guardarEmpleado(empleado));
        verify(mockRepository, times(1)).save(empleado);
    }
}
