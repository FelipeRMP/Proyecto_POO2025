package proyecto_poo.modelo.entidad;
import java.util.ArrayList;
import java.util.Arrays;

public class habitacion {
    private int numero;
    private int capacidadMaxima;
    private double precioPorNoche;
    private String tipo;
    private estado_habitacion estado;



    //constructor
    public habitacion(int numero, int capacidadMaxima, double precioPorNoche, String tipo, estado_habitacion estado){
        this.numero = numero;
        this.capacidadMaxima = capacidadMaxima;
        this.precioPorNoche = precioPorNoche;
        this.tipo = tipo;
        this.estado = estado_habitacion.Disponible;
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
    public estado_habitacion getEstado(){
        return estado;
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
    public void setEstado(estado_habitacion estado){
        this.estado = estado;
    }



}
