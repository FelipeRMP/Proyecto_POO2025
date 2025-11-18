package proyecto_poo.modelo.entidad;

public class serviciosAdicionales {
    private String nombre;
    private double precio;

    //constructor
    public serviciosAdicionales(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
    }
    //getters
    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    //setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
}
