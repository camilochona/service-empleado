# 🧩 Proyecto de Evaluación Técnica - Parameta S.A.S
Este proyecto consiste en una solucion **Java con Spring Boot** que expone un servicio REST para recibir y validar los
datos de un empleado. Una vez validados, el sistema calcula su edad y tiempo de vinculacin, y finalmente envia los
datos a un **servicio SOAP** que los persiste en una base de datos **MySQL**.
---
## 📐 Arquitectura del Proyecto
El proyecto esta basado en una arquitectura en capas siguiendo el patrn MVC:
- **Controlador (Controller)**: Maneja las solicitudes REST (`EmpleadoController`).
- **Entidad (Entity)**: Representa el modelo de datos (`Empleado`).
- **Servicio SOAP (Service)**: Define e implementa la logica de persistencia a traves de un cliente SOAP
(`EmpleadoService`, `EmpleadoServiceImpl`).
- **Manejo de excepciones globales**: Captura errores de validacion y de negocio con `GlobalExceptionHandler`.
- **Configuracin SOAP**: `SoapWebServiceConfig` permite integrar el WSDL generado.
---
## 🔧 Funcionalidad Principal
1. El usuario envia los datos de un empleado va `POST` al endpoint REST `/api/empleado`.
2. Se realizan validaciones:
 - Todos los campos deben estar presentes.
 - El empleado debe tener al menos 18 años.
 - La fecha de vinculacin no puede ser anterior a la fecha de nacimiento.
 - El salario debe ser positivo.
3. Si las validaciones son correctas:
 - Se calcula la edad actual del empleado.
 - Se calcula el tiempo de vinculacion.
4. Se invoca el servicio SOAP para almacenar el objeto `Empleado` en la base de datos.
5. Se devuelve el empleado como JSON, incluyendo los campos calculados.
---
## 🧪 Validaciones Aplicadas
- Todos los campos son obligatorios.
- `nombres`, `apellidos`, `cargo` y `numeroDocumento` no pueden estar vacos.
- `salario` debe ser mayor a 0.
- El empleado debe ser mayor de edad.
- La fecha de vinculacin no puede ser anterior a la fecha de nacimiento.
- Las fechas deben estar en formato ISO (`yyyy-MM-dd`).
- Los errores son gestionados de forma global mediante `@ControllerAdvice`.
---
## 📦 Dependencias Principales
- Java 8+
- Spring Boot
- Spring Web
- Spring Validation (JSR-303)
- Spring SOAP (JAX-WS)
- MySQL
- Spring Data JPA
- JUnit 5 + Mockito + Spring Test
- Maven
---
## 📂 Estructura del Proyecto
src/
├── main/
│ ├── java/
│ │ └── com.parameta.empleado/
│ │ ├── controller/ → Controlador REST
│ │ ├── entity/ → Clase Empleado
│ │ ├── soap/ → Cliente SOAP y lógica de negocio
│ │ ├── config/ → Configuración de servicios
│ │ └── exception/ → Manejador global de excepciones
├── test/
│ └── java/com.parameta.empleado/
│ └── controller/ → Pruebas unitarias del controller
---
## 🚀 Endpoint de Prueba
- POST http://localhost:8081/api/empleado
- Json:
    {
        "nombres": "camilo",
        "apellidos": "chona",
        "tipoDocumento": "CC",
        "numeroDocumento": "123456789",
        "fechaNacimiento": "2000-09-05T00:00:00.000+00:00",
        "fechaVinculacion": "2021-01-15T00:00:00.000+00:00",
        "cargo": "Desarrollador",
        "salario": 8.000000
    }
---
## ✅ Pruebas Realizadas
Las pruebas estan implementadas con **JUnit 5**, **Mockito** y **Spring Test** mediante `@WebMvcTest`, cubriendo:
- Casos exitosos de registro.
- Validaciones negativas: menor de edad, salario negativo, fechas inconsistentes, campos vacios.
- Verificacion de que los campos calculados (`edadFormateada`, `tiempoVinculacionFormateado`) esten presentes.
---
## 🧠 Autor
Desarrollado por **Camilo Chona** como parte del proceso tcnico para **Parameta S.A.S**
---
## 📌 Notas Adicionales
- El proyecto corre en `localhost:8081`
- Asegrate de tener activo un servidor MySQL con la base de datos `clientes` creada.
- Incluye todas las dependencias necesarias para consumir y exponer servicios SOAP con WSDL.
- Se puede probar tanto desde Postman como desde pruebas automatizadas.
