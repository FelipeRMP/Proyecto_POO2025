package proyecto_poo.controlador;

import proyecto_poo.modelo.usuario.usuario;

public class ControladorMain {

    private baseDeDatos db; // Instancia de la base de datos

    public ControladorMain() {
        // Inicializamos la base de datos para que el controlador pueda usarla
        this.db = new baseDeDatos();
    }

    /**
     * Autentica un usuario utilizando la base de datos.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return El objeto 'usuario' si la autenticación es exitosa, o 'null' si falla.
     */
    public usuario autenticarUsuario(String username, String password) {
        System.out.println("Llamando a la base de datos para autenticar a: " + username);
        // Llama al método de validación real en la base de datos
        return db.validarLogin(username, password);
    }
}
