package proyecto_poo.modelo.usuario;

public abstract class usuario {
    private String codigo_usuario;
    private String clave;

    public usuario(String codigo_usuario, String clave){
        this.codigo_usuario = codigo_usuario;
        this.clave = clave;
    }

}
