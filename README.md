# Proyecto POO2025
User admin: admin

pass admin: admin123


User Recepcionista: recepcion123

pass recepcionista: recepcion123

Para ejecutar se encuentra un metodo main en la clase Login o usar mvn compile exec:java en consola.

Este proyecto es una aplicación Java desarrollada siguiendo el patrón arquitectónico Modelo-Vista-Controlador (MVC). Es un sistema de reservas de hotel.

## Estructura de Carpetas

La estructura principal del proyecto es la siguiente:

- `src/main/java/proyecto_poo/controlador`: Contiene la lógica de control de la aplicación, manejando las interacciones entre el Modelo y la Vista.
- `src/main/java/proyecto_poo/modelo`: Contiene la lógica de negocio y las clases de entidad (`habitacion`, `huesped`, `reserva`, `serviciosAdicionales`, `usuario`, `admin`, `recepcionista`).
- `src/main/java/proyecto_poo/vista`: Contiene las interfaces de usuario (Vistas) de la aplicación, como `auth.java` y `menu.java`.
- `src/main/resources`: Recursos adicionales, como `instrucciones.txt`.
- `pom.xml`: Archivo de configuración de Maven para la gestión de dependencias y el ciclo de vida del proyecto.

