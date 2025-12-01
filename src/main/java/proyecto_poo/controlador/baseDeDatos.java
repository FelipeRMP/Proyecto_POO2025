package proyecto_poo.controlador;

import proyecto_poo.modelo.entidad.estado_habitacion;
import proyecto_poo.modelo.entidad.habitacion;
import proyecto_poo.modelo.entidad.huesped;
import proyecto_poo.modelo.entidad.reserva;
import proyecto_poo.modelo.entidad.serviciosAdicionales;
import proyecto_poo.modelo.usuario.recepcionista;
import proyecto_poo.modelo.usuario.tipo_usuario;
import proyecto_poo.modelo.usuario.admin;
import proyecto_poo.modelo.usuario.usuario;


import java.util.ArrayList;
import java.util.Arrays;

public class baseDeDatos {


    private ArrayList<huesped> lista_huespedes = new ArrayList<>(
            Arrays.asList(

            )
    );

    public void registrarHuesped(int dni, int telefono, String nombres, String  apellidos, String correo){
        huesped nuevo_huesped = new huesped(dni, telefono, nombres, apellidos, correo);
        lista_huespedes.add(nuevo_huesped);
    }
    public void eliminarHuesped(huesped huesped_borrado){
        lista_huespedes.remove(huesped_borrado);
    }
    public void modificarHuesped(){

    }


    /// /////////

    private ArrayList<reserva> reservas = new ArrayList<>();

    public void agregarReserva(reserva nuevaReserva) {
        reservas.add(nuevaReserva);
        System.out.println("Reserva agregada exitosamente para el huésped: " + nuevaReserva.getHuesped().getNombres());
    }

    public ArrayList<reserva> getReservas() {
        return reservas;
    }

    public reserva buscarReservaPorDniHuesped(int dni) {
        for (reserva r : reservas) {
            if (r.getHuesped().getDni() == dni) {
                return r;
            }
        }
        return null; // No se encontró la reserva
    }

    public void actualizarReserva(reserva reservaActualizada) {
        for (int i = 0; i < reservas.size(); i++) {
            reserva r = reservas.get(i);
            if (r.getHuesped().getDni() == reservaActualizada.getHuesped().getDni()) {
                // Actualizamos la reserva en la lista
                reservas.set(i, reservaActualizada);
                System.out.println("Reserva actualizada para el DNI: " + reservaActualizada.getHuesped().getDni());
                return; // Salimos del método una vez actualizada
            }
        }
    }


    private ArrayList<habitacion> habitaciones = new ArrayList<>(
            Arrays.asList(  
                    new habitacion(101, 2, 99.99, "simple", estado_habitacion.Disponible),
                    new habitacion(102, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(103, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(104, 4, 249.99, "suite", estado_habitacion.Disponible),
                    new habitacion(201, 2, 99.99, "simple", estado_habitacion.Disponible),
                    new habitacion(202, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(203, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(204, 4, 249.99, "suite", estado_habitacion.Disponible),
                    new habitacion(301, 2, 99.99, "simple", estado_habitacion.Disponible),
                    new habitacion(302, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(303, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(304, 4, 249.99, "suite", estado_habitacion.Disponible),
                    new habitacion(401, 2, 99.99, "simple", estado_habitacion.Disponible),
                    new habitacion(402, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(403, 4, 189.99, "doble", estado_habitacion.Disponible),
                    new habitacion(404, 4, 249.99, "suite", estado_habitacion.Disponible)
            )
    );

    public ArrayList<habitacion> getHabitaciones() {
        return habitaciones;
    }

    public habitacion getHabitacionPorNumero(int numero) {
        for (habitacion hab : habitaciones) {
            if (hab.getNumero() == numero) {
                return hab;
            }
        }
        return null;
    }

    public void crearHabitacion(int numero, int capacidadMaxima, double precioPorNoche, String tipo, estado_habitacion estado){
        habitaciones.add(new habitacion(numero, capacidadMaxima, precioPorNoche, tipo, estado));
    }
    public void eliminarHabitacion(habitacion habitacion_borrada){
       habitaciones.remove(habitacion_borrada);
    }
    public void modificarHabitacion(){

    }




    /// ///////






    private ArrayList<serviciosAdicionales> servicios = new ArrayList<>(
            Arrays.asList(
                    new serviciosAdicionales("Desayuno buffet", 49.90),
                    new serviciosAdicionales("Servicio al cuarto", 25.00),
                    new serviciosAdicionales("Spa y sauna", 120.00),
                    new serviciosAdicionales("Transporte al aeropuerto", 85.00),
                    new serviciosAdicionales("Lavandería", 15.00),
                    new serviciosAdicionales("Early Check-In", 60.00),
                    new serviciosAdicionales("Late Check-Out", 70.00),
                    new serviciosAdicionales("Tour por la ciudad", 150.00)
            )
    );

    public void registrarServicioAdicional(String nombre, double precio) {
        serviciosAdicionales nuevo_servicio = new serviciosAdicionales(nombre, precio);
        servicios.add(nuevo_servicio);
    }




    public ArrayList<serviciosAdicionales> getServicios() {
        return servicios;
    }
    
    
    
    ////////
    
    
    private ArrayList<admin> lista_admins = new ArrayList<>(
            Arrays.asList(
                    new admin("admin", "admin123", tipo_usuario.ADMINISTRADOR)
            )
    );

    public ArrayList<admin> getListaAdmins() {
        return lista_admins;
    }

    public void crearAdmin(String nombre, String clave, tipo_usuario tipo){
        admin nuevo_admin = new admin(nombre, clave, tipo);
        lista_admins.add(nuevo_admin);
    }
    public void eliminarAdmin(admin admin_borrado){
        lista_admins.remove(admin_borrado);
    }
    public void modificarAdmin(){

    }
    public usuario buscarEmpleadoPorNombreUsuario(String nombreUsuario) {
        for (admin a : lista_admins) {
            if (a.getNombre_usuario().equals(nombreUsuario)) {
                return a;
            }
        }
        for (recepcionista r : lista_recepcionistas) {
            if (r.getNombre_usuario().equals(nombreUsuario)) {
                return r;
            }
        }
        return null;
    }

    public void actualizarEmpleado(usuario empleadoActualizado) {
        if (empleadoActualizado instanceof admin) {
            admin adminActualizado = (admin) empleadoActualizado;
            for (int i = 0; i < lista_admins.size(); i++) {
                if (lista_admins.get(i).getNombre_usuario().equals(adminActualizado.getNombre_usuario())) {
                    lista_admins.set(i, adminActualizado);
                    System.out.println("Administrador " + adminActualizado.getNombre_usuario() + " actualizado.");
                    return;
                }
            }
        } else if (empleadoActualizado instanceof recepcionista) {
            recepcionista recepcionistaActualizado = (recepcionista) empleadoActualizado;
            for (int i = 0; i < lista_recepcionistas.size(); i++) {
                if (lista_recepcionistas.get(i).getNombre_usuario().equals(recepcionistaActualizado.getNombre_usuario())) {
                    lista_recepcionistas.set(i, recepcionistaActualizado);
                    System.out.println("Recepcionista " + recepcionistaActualizado.getNombre_usuario() + " actualizado.");
                    return;
                }
            }
        }
        System.out.println("Error: Empleado no encontrado para actualizar.");
    }

    public void crearRecepcionista(String nombre, String clave, tipo_usuario tipo) {
        recepcionista nuevo_recepcionista = new recepcionista(nombre, clave, tipo);
        lista_recepcionistas.add(nuevo_recepcionista);
    }

    private ArrayList<recepcionista> lista_recepcionistas = new ArrayList<>(
            Arrays.asList(
                    new recepcionista("recepcion", "recepcion123", tipo_usuario.RECEPCIONISTA)
            )
    );

    public ArrayList<recepcionista> getListaRecepcionistas() {
        return lista_recepcionistas;
    }

    public void agregarEmpleado(String nombre, String clave, tipo_usuario tipo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (clave == null || clave.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de usuario válido");
        }

        switch (tipo) {
            case ADMINISTRADOR -> lista_admins.add(new admin(nombre.trim(), clave, tipo_usuario.ADMINISTRADOR));
            case RECEPCIONISTA -> lista_recepcionistas.add(new recepcionista(nombre.trim(), clave, tipo_usuario.RECEPCIONISTA));
        }
    }

    public reserva getReservaActivaPorHabitacion(int numHabitacion) {
        for (reserva r : reservas) {
            if (r.getHabitacion() != null && r.getHabitacion().getNumero() == numHabitacion && r.getCheckIn()) {
                return r;
            }
        }
        return null; // No se encontró una reserva activa para esa habitación
    }

    // En proyecto_poo/controlador/baseDeDatos.java


    public usuario validarLogin(String user, String pass) {
        // 1. Buscar en Admins
        for (admin a : lista_admins) {
            if (a.getNombre_usuario().equals(user) && a.getClave().equals(pass)) {
                return a; // Retornamos el usuario encontrado
            }
        }
        // 2. Buscar en Recepcionistas
        for (recepcionista r : lista_recepcionistas) {
            if (r.getNombre_usuario().equals(user) && r.getClave().equals(pass)) {
                return r;
            }
        }
        return null; // Si no encuentra nada
    }

}
