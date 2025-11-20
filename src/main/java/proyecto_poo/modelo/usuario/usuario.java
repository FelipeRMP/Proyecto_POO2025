package proyecto_poo.modelo.usuario;

public abstract class usuario {
    private String nombre_usuario;
    private String clave;
    private tipo_usuario tipo;

    public usuario(String nombre_usuario, String clave, tipo_usuario tipo){
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
        this.tipo = tipo;
    }

    //getters

    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public String getClave() {
        return clave;
    }
    public tipo_usuario getTipo() {
        return tipo;
    }

    //setters
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public void setTipo(tipo_usuario tipo) {
        this.tipo = tipo;
    }
}
