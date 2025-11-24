package proyecto_poo.modelo.usuario;

public class recepcionista extends usuario {

    //constructor

    public recepcionista(String nombre_usuario, String clave, tipo_usuario recepcionista) {
        super(nombre_usuario, clave, tipo_usuario.RECEPCIONISTA);
    }

}
