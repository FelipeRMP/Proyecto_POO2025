# Proyecto POO2025

Este proyecto es una aplicación Java desarrollada siguiendo el patrón arquitectónico Modelo-Vista-Controlador (MVC). Parece ser un sistema de gestión para reservas de hotel o un sistema similar, dado los módulos de `habitacion`, `huesped`, `reserva` y `serviciosAdicionales`.

## Estructura de Carpetas

La estructura principal del proyecto es la siguiente:

- `src/main/java/proyecto_poo/controlador`: Contiene la lógica de control de la aplicación, manejando las interacciones entre el Modelo y la Vista.
- `src/main/java/proyecto_poo/modelo`: Contiene la lógica de negocio y las clases de entidad (`habitacion`, `huesped`, `reserva`, `serviciosAdicionales`, `usuario`, `admin`, `recepcionista`).
- `src/main/java/proyecto_poo/vista`: Contiene las interfaces de usuario (Vistas) de la aplicación, como `auth.java` y `menu.java`.
- `src/main/resources`: Recursos adicionales, como `instrucciones.txt`.
- `pom.xml`: Archivo de configuración de Maven para la gestión de dependencias y el ciclo de vida del proyecto.

## Cómo Compilar y Ejecutar

Este proyecto utiliza Apache Maven para la gestión de dependencias y la compilación.

Para compilar el proyecto:

```bash
mvn clean install
```

Para ejecutar la aplicación (suponiendo que existe una clase `main` en `ControladorMain`):

```bash
mvn exec:java -Dexec.mainClass="proyecto_poo.controlador.ControladorMain"
```

## Dependencias

El proyecto gestiona sus dependencias a través de Maven. Las dependencias específicas se encuentran en el archivo `pom.xml`.
