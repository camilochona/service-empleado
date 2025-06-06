# 🧩 Proyecto de Evaluación Técnica - Parameta S.A.S

Este proyecto consiste en una solución fullstack en Java que expone un servicio REST encargado de recibir los datos de un empleado, realizar validaciones y cálculos, y luego invocar un servicio SOAP para almacenar la información en una base de datos MySQL.

## 📐 Arquitectura del Proyecto

El proyecto está estructurado siguiendo el patrón **MVC (Modelo - Vista - Controlador)** y separado en las siguientes capas:

- **Controlador (Controller):** Exposición del servicio REST (`EmpleadoController`)
- **Servicio (Service):** Encapsula la lógica de negocio y comunicación con el servicio SOAP (`EmpleadoService`, `EmpleadoServiceImpl`)
- **Entidad (Entity):** Representación del modelo de datos (`Empleado`)
- **SOAP Web Service:** Exposición de un servicio SOAP que persiste los datos en base de datos.
- **Configuración:** Configuración del servicio SOAP (`SoapWebServiceConfig`)

## 🧪 Lógica del Negocio

Cuando se consume el endpoint REST:

1. Se reciben los parámetros del empleado.
2. Se hacen las siguientes **validaciones**:
   - Todos los campos obligatorios deben estar presentes.
   - El empleado debe ser mayor de edad (mínimo 18 años).
3. Se calcula:
   - **Edad actual**: En años, meses y días.
   - **Tiempo de vinculación**: En años, meses y días.
4. Si todo es válido se crea un objeto `Empleado`, se envía al servicio SOAP y se guarda en MySQL.
5. Se retorna el objeto `Empleado` como JSON incluyendo los campos adicionales calculados.

## 📦 Dependencias principales

- Java 8+
- Spring Boot
- JAX-WS (SOAP)
- MySQL
- Maven
- Postman (para pruebas)

#Ejemplo de prueba en Postman:**

http://localhost:8081/empleado?nombres=Juan&apellidos=Chona&tipoDocumento=CC&numeroDocumento=123456789&fechaNacimiento=2000-11-11&fechaVinculacion=2020-01-01&cargo=Desarrollador&salario=8000000

## 🧪 Validaciones aplicadas

- Todos los campos son obligatorios
- La edad mínima del empleado debe ser **18 años**
- Fechas deben estar en formato ISO (`yyyy-MM-dd`)

## 🧠 Autor

Desarrollado por Camilo Chona como parte del proceso técnico para **Parameta S.A.S**

## 📌 Notas adicionales

- El proyecto está pensado para correr localmente en `localhost:8081`
- Asegúrate de tener MySQL activo y una base de datos llamada `clientes`
- Incluye dependencias de JAX-WS para exponer el servicio SOAP

