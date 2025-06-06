package com.parameta.empleado.soap;


import javax.jws.WebMethod;
import javax.jws.WebService;
import com.parameta.empleado.entity.Empleado;

@WebService
public interface EmpleadoService {

    @WebMethod
    String guardarEmpleado(Empleado empleado);
}