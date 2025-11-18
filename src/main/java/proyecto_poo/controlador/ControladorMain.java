import proyecto_poo.modelo.entidad.habitacion;
import proyecto_poo.modelo.usuario.admin;
import proyecto_poo.modelo.usuario.recepcionista;
import proyecto_poo.modelo.usuario.usuario;

import java.util.Scanner;

public class ControladorMain {

    habitacion[] habitaciones = {
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
    };

    usuario[] usuarios = {
            new admin("admin", "admin"),
            new recepcionista("recepcion", "1234")
    };

}

public static void main(String[] args) {

}

