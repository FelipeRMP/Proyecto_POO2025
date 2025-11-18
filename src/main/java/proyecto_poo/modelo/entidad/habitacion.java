package proyecto_poo.modelo.entidad;

public class habitacion {
    private int numero;
    private int capacidadMaxima;
    private double precioPorNoche;
    private String tipo;

    //constructor
    public habitacion(int numero, int capacidadMaxima, double precioPorNoche, String tipo){
        this.numero = numero;
        this.capacidadMaxima = capacidadMaxima;
        this.precioPorNoche = precioPorNoche;
        this.tipo = tipo;
    }

    //getters
    public int getNumero(){
        return numero;
    }
    public int getCapacidadMaxima(){
        return capacidadMaxima;
    }
    public double getPrecioPorNoche(){
        return precioPorNoche;
    }
    public String getTipo(){
        return tipo;
    }

    //setters
    public void setNumero(int numero){
        this.numero = numero;
    }
    public void setCapacidadMaxima(int capacidadMaxima){
        this.capacidadMaxima = capacidadMaxima;
    }
    public void setPrecioPorNoche(double precioPorNoche){
        this.precioPorNoche = precioPorNoche;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }



}
