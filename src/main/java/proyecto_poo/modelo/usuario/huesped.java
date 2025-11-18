package proyecto_poo.modelo.usuario;

public class huesped {
    private int dni;
    private int telefono;
    private String nombres;
    private String apellidos;
    private String correo;

    // constructor

    public huesped(int dni, int telefono, String nombres, String  apellidos, String correo){
        this.dni = dni;
        this.telefono = telefono;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    //getters

    public int getDni(){
        return dni;
    }
    public int getTelefono(){
        return telefono;
    }
    public String getNombres(){
        return nombres;
    }
    public String getApellidos(){
        return apellidos;
    }
    public String getCorreo(){
        return correo;
    }

    //setters

    public void setDni(int dni){
        this.dni = dni;
    }
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
    public void setNombres(String nombres){
        this.nombres = nombres;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
}
