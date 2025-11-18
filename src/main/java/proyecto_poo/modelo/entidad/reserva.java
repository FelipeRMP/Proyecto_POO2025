package proyecto_poo.modelo.entidad;

import java.time.LocalDate;

public class reserva {
    //atributos
    private huesped huesped;
    private habitacion habitacion;
    private serviciosAdicionales serviciosAdicionales;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    //constructor
    public reserva(huesped huesped, habitacion habitacion, serviciosAdicionales serviciosAdicionales, LocalDate fechaInicio, LocalDate fechaFin){
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.serviciosAdicionales = serviciosAdicionales;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    //getters
    public huesped getHuesped(){
        return huesped;
    }
    public habitacion getHabitacion(){
        return habitacion;
    }
    public serviciosAdicionales getServiciosAdicionales(){
        return serviciosAdicionales;
    }
    public LocalDate getFechaInicio(){
        return fechaInicio;
    }
    public LocalDate getFechaFin(){
        return fechaFin;
    }
    //setters
    public void setHuesped(huesped huesped){
        this.huesped = huesped;
    }
    public void setHabitacion(habitacion habitacion){
        this.habitacion = habitacion;
    }
    public void setServiciosAdicionales(serviciosAdicionales serviciosAdicionales){
        this.serviciosAdicionales = serviciosAdicionales;
    }
    public void setFechaInicio(LocalDate fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin(LocalDate fechaFin){
        this.fechaFin = fechaFin;
    }
}

