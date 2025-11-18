package proyecto_poo.prototipo;

public abstract class Usuario {
    private String codigo_usuario;
    private String clave;

    public Usuario(String codigo_usuario, String clave){
        this.codigo_usuario = codigo_usuario;
        this.clave = clave;
    }

}
