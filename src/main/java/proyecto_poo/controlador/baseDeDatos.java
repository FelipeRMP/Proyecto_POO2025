package proyecto_poo.controlador;

import proyecto_poo.modelo.entidad.estado_habitacion;
import proyecto_poo.modelo.entidad.habitacion;
import proyecto_poo.modelo.entidad.huesped;
import proyecto_poo.modelo.entidad.serviciosAdicionales;
import proyecto_poo.modelo.usuario.tipo_usuario;
import proyecto_poo.modelo.usuario.admin;

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
    private ArrayList<recepcionista> lista_recepcionistas = new ArrayList<>(
            Arrays.asList(
                    new recepcionista("recepcion", "hotel123", tipo_usuario.RECEPCIONISTA)
            )
    );
    public void crearAdmin(String nombre, String clave, tipo_usuario tipo){
        admin nuevo_admin = new admin(nombre, clave, tipo);
        lista_admins.add(nuevo_admin);
    }
    public void eliminarAdmin(admin admin_borrado){
        lista_admins.remove(admin_borrado);
    }
    public void modificarAdmin(){

    }
    



}
