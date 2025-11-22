import proyecto_poo.controlador.baseDeDatos;
import proyecto_poo.modelo.entidad.*;
import proyecto_poo.modelo.usuario.*;

public class ControladorMain {



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


    /// // LISTO, @REVISAR BASE DE DATOS


    /*3.	El sistema debe permitir al Recepcionista registrar, modificar y
    eliminar Huéspedes, incluyendo datos como DNI, nombres, apellidos y datos de contacto. */


    /// ///// // LISTO, @REVISAR BASE DE DATOS



    //4.	El sistema debe permitir al Administrador registrar, modificar y eliminar Servicios Adicionales que \
    //el hotel ofrece (ej. Lavandería, Servicio a la habitación, Frigobar), cada uno con un nombre y un precio.



    /// // // LISTO, @REVISAR BASE DE DATOS



    //5.	El sistema debe permitir al Recepcionista crear una Reservación para un Huésped, asignando un rango de
    // fechas y un tipo de habitación. El sistema debe validar la disponibilidad de habitaciones de ese
    // tipo para las fechas solicitadas.

    public void crearReserva(huesped huesped, habitacion habitacion, serviciosAdicionales serviciosAdicionales,
                             LocalDate fechaInicio){

    }



}

    /*6.	El sistema debe gestionar la Estadía del huésped:
    Check-in: El Recepcionista debe poder registrar el ingreso de un huésped (basado en una reservación),
    asignándole una habitación específica que esté disponible y registrada como "Limpia".
    Check-out: El Recepcionista debe poder registrar la salida del huésped. Al hacerlo, la habitación
    asignada debe pasar a estado "Sucia" o "En limpieza".*/


    //LISTO, REVISAR @RESERVA


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

