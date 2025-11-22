package proyecto_poo.modelo.entidad;
import proyecto_poo.controlador.baseDeDatos;
import java.time.LocalDate;

public class reserva {
    //atributos
    private huesped huesped;
    private habitacion habitacion;
    private serviciosAdicionales serviciosAdicionales;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean checkIn;
    private int habitacion_reserva;


    //constructor
    public reserva(huesped huesped, habitacion habitacion, serviciosAdicionales serviciosAdicionales,
                   LocalDate fechaInicio, LocalDate fechaFin, boolean checkIn){
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.serviciosAdicionales = serviciosAdicionales;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.checkIn = checkIn;

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
    public boolean getCheckIn(){
        return checkIn;
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
    public void setCheckIn(boolean checkIn){
        this.checkIn = checkIn;
    }

    public void registrarReserva(huesped huesped, habitacion habitacion, serviciosAdicionales serviciosAdicionales, LocalDate fechaInicio, LocalDate fechaFin){

    }

    public void checkIn(boolean checkIn){
        setCheckIn(true);
        for (habitacion h : baseDeDatos.habitaciones) {
            if (habitacion.getEstado() == estado_habitacion.Disponible){
                int habitacion_reserva = habitacion.getNumero();
            }
        }
        habitacion.setEstado(estado_habitacion.Ocupada);
    }

    public void checkOut(boolean checkIn){
        for (habitacion h : baseDeDatos.habitaciones) {
            habitacion.setEstado(estado_habitacion.En_Limpieza);
        }
    }


}

