package proyecto_poo.modelo.usuario;

public abstract class usuario {
    private String nombre_usuario;
    private String clave;

    public usuario(String nombre_usuario, String clave){
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
    }

}
