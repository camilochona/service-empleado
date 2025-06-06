package com.parameta.empleado.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parameta.empleado.entity.Empleado;
import com.parameta.empleado.repository.EmpleadoRepository;

import javax.jws.WebService;

@WebService(endpointInterface = "com.parameta.empleado.soap.EmpleadoService")
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public String guardarEmpleado(Empleado empleado) {
    	empleadoRepository.save(empleado);
        return "Empleado almacenado correctamente";
    }
}