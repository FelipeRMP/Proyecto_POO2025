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


    public void checkIn() { // No hace falta pasar el boolean por parámetro, se asume true
        // 1. Actualizamos el estado interno de la reserva
        this.checkIn = true;

        // 2. Validamos habitación asignada y que esté disponible
        if (this.habitacion != null && this.habitacion.getEstado() == estado_habitacion.Disponible) {

            // 3. Modificamos DIRECTAMENTE el objeto habitación vinculado
            this.habitacion.setEstado(estado_habitacion.Ocupada);
            System.out.println("Check-in realizado con éxito en la habitación " + this.habitacion.getNumero());

        } else {
            System.out.println("Error: La habitación no está disponible o no existe.");
        }
    }

    public void checkOut() {
        // 1. Modificamos DIRECTAMENTE el objeto habitación vinculado
        if (this.habitacion != null) {
            this.habitacion.setEstado(estado_habitacion.En_Limpieza); // O Disponible, según tu lógica
            System.out.println("Check-out realizado. Habitación " + this.habitacion.getNumero() + " enviada a limpieza.");
        }
    }


}

