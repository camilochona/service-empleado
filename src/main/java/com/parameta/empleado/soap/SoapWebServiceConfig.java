package com.parameta.empleado.soap;

import javax.xml.ws.Endpoint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.parameta.empleado.repository.EmpleadoRepository;
import com.parameta.empleado.soap.EmpleadoServiceImpl;

@Component
public class SoapWebServiceConfig implements CommandLineRunner {

    private final EmpleadoRepository empleadoRepository;

    public SoapWebServiceConfig(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        Endpoint.publish("http://localhost:8080/empleado", new EmpleadoServiceImpl(empleadoRepository));
        System.out.println("Servicio SOAP publicado en http://localhost:8080/empleado");
    }
}