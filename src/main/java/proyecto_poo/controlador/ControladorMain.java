import proyecto_poo.modelo.entidad.*;
import proyecto_poo.modelo.usuario.*;





public class ControladorMain {

    ArrayList<habitacion> habitaciones = new ArrayList<>(
            Arrays.asList(
                    new habitacion(101, 2, 99.99, "simple"),
                    new habitacion(102, 4, 189.99, "doble"),
                    new habitacion(103, 4, 189.99, "doble"),
                    new habitacion(104, 4, 249.99, "suite"),
                    new habitacion(201, 2, 99.99, "simple"),
                    new habitacion(202, 4, 189.99, "doble"),
                    new habitacion(203, 4, 189.99, "doble"),
                    new habitacion(204, 4, 249.99, "suite"),
                    new habitacion(301, 2, 99.99, "simple"),
                    new habitacion(302, 4, 189.99, "doble"),
                    new habitacion(303, 4, 189.99, "doble"),
                    new habitacion(304, 4, 249.99, "suite"),
                    new habitacion(401, 2, 99.99, "simple"),
                    new habitacion(402, 4, 189.99, "doble"),
                    new habitacion(403, 4, 189.99, "doble"),
                    new habitacion(404, 4, 249.99, "suite")
            )
    );

    ArrayList<usuario> usuario = new ArrayList<>(
            Arrays.asList(
                    new recepcionista()
            )
    );




    /*
    1. El sistema debe permitir al Administrador crear, modificar y eliminar Empleados
     (Recepcionistas, Administradores), ingresando datos como DNI, nombres, apellidos y rol.
    */

    ArrayList<admin> lista_admins = new ArrayList<>();
    ArrayList<recepcionista> lista_recepcionistas = new ArrayList<>();


    public void crearAdmin(String nombre, String clave){
        admin nuevo_admin = new admin(nombre, clave);
        lista_admins.add(nuevo_admin);
    }
    public void eliminarAdmin(admin admin_borrado){
        lista_admins.remove(admin_borrado);
    }
    public void modificarAdmin(){

    }


    public void crearRecepcionista(String nombre, String clave){
        recepcionista nuevo_recepcionista = new recepcionista(nombre, clave);
        lista_recepcionistas.add(nuevo_recepcionista);
    }
    public void eliminarRecepcionista(recepcionista recepcionista_borrado){
        lista_recepcionistas.remove(recepcionista_borrado);
    }
    public void modificarRecepcionista(){

    }

    //2. El sistema debe permitir al Administrador registrar, modificar y eliminar Habitaciones.
    ArrayList<habitacion> lista_habitaciones = new ArrayList<>();

    public void crearHabitacion(int numero, int capacidadMaxima, double precioPorNoche, String tipo){
        habitacion nueva_habitacion = new habitacion(numero, capacidadMaxima, precioPorNoche, tipo);
    }
    public void eliminarHabitacion(habitacion habitacion_borrada){
        lista_habitaciones.remove(habitacion_borrada);
    }
    public void modificarHabitacion(){

    }


    /*3.	El sistema debe permitir al Recepcionista registrar, modificar y
    eliminar Huéspedes, incluyendo datos como DNI, nombres, apellidos y datos de contacto. */

    ArrayList<huesped> lista_huespedes = new ArrayList<>();

    public void registrarHuesped(int dni, int telefono, String nombres, String  apellidos, String correo){
        huesped nuevo_huesped = new huesped(dni, telefono, nombres, apellidos, correo);
        lista_huespedes.add(nuevo_huesped);
    }
    public void eliminarHuesped(huesped huesped_borrado){
        lista_huespedes.remove(huesped_borrado);
    }
    public void modificarHuesped(){

    }


    //4.	El sistema debe permitir al Administrador registrar, modificar y eliminar Servicios Adicionales que \
    //el hotel ofrece (ej. Lavandería, Servicio a la habitación, Frigobar), cada uno con un nombre y un precio.

    ArrayList<serviciosAdicionales> lista_servicios = new ArrayList<>();

    public void registrarServicio(){

    }

    //5.	El sistema debe permitir al Recepcionista crear una Reservación para un Huésped, asignando un rango de
    // fechas y un tipo de habitación. El sistema debe validar la disponibilidad de habitaciones de ese
    // tipo para las fechas solicitadas.



    public void check_in(){

    }

}

    /*6.	El sistema debe gestionar la Estadía del huésped:
    Check-in: El Recepcionista debe poder registrar el ingreso de un huésped (basado en una reservación),
    asignándole una habitación específica que esté disponible y registrada como "Limpia".
    Check-out: El Recepcionista debe poder registrar la salida del huésped. Al hacerlo, la habitación
    asignada debe pasar a estado "Sucia" o "En limpieza".*/





    /*7.	Durante la estadía activa de un huésped, el Recepcionista debe poder registrar consumos de
     Servicios Adicionales (ej. 2 servicios de lavandería, 1 consumo de frigobar).*/






    /*8.	El sistema debe realizar el cálculo automático de la facturación al momento del Check-out. Debe
    sumar el costo total de las noches de estadía (precio de la habitación * noches) más el costo de todos los
    servicios adicionales consumidos.*/







    /*  9.	El sistema debe permitir la generación de informes básicos:
    -	Reporte de ocupación de habitaciones (qué habitaciones están ocupadas/libres).
    -	Reporte de ingresos generados en un rango de fechas, desglosado por costo de
     habitaciones y costo de servicios. */






     /* 10.	El sistema debe contar con un mecanismo de autenticación (login y password) para Empleados. Las funciones
     deben estar restringidas por rol (ej. solo el Administrador puede registrar habitaciones, el Recepcionista solo
     gestiona huéspedes y reservas).*/







     /* 11. El sistema debe contar con una interfaz de usuario intuitiva y accesible, que facilite al
     operador la realización eficiente de sus tareas.*/


public static void main(String[] args) {

}

