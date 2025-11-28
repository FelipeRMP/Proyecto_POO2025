package proyecto_poo.controlador;

import proyecto_poo.modelo.usuario.usuario;

public class ControladorMain {

    private baseDeDatos db; // Instancia de la base de datos

    //getter base de datos
    public baseDeDatos getDb(){
        return db;
    }

    public ControladorMain() {
        // Inicializamos la base de datos para que el controlador pueda usarla
        this.db = new baseDeDatos();
    }

    public usuario autenticarUsuario(String username, String password) {
        System.out.println("Llamando a la base de datos para autenticar a: " + username);
        // Llama al metodo de validcion
        return db.validarLogin(username, password);
    }
}
