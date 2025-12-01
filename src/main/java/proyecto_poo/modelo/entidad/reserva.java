package proyecto_poo.modelo.entidad;
import proyecto_poo.controlador.baseDeDatos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class reserva {
    //atributos
    private huesped huesped;
    private habitacion habitacion;
    private List<serviciosAdicionales> serviciosAdicionales;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean checkIn;


    //constructor
    public reserva(huesped huesped, habitacion habitacion,
                   LocalDate fechaInicio, LocalDate fechaFin, boolean checkIn){
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.serviciosAdicionales = new ArrayList<>();
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
    public List<serviciosAdicionales> getServiciosAdicionales(){
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
    public void setServiciosAdicionales(List<serviciosAdicionales> serviciosAdicionales){
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

    public void agregarServicioAdicional(serviciosAdicionales servicio) {
        if (this.serviciosAdicionales == null) {
            this.serviciosAdicionales = new ArrayList<>();
        }
        this.serviciosAdicionales.add(servicio);
    }

    public void registrarReserva(huesped huesped, habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin){

    }

    public void checkIn() { // No hace falta pasar el boolean por parámetro, se asume true
        // 1. Actualizamos el estado interno de la reserva
        this.checkIn = true;

        // 2. Validamos habitación asignada y que esté disponible
        if (this.habitacion != null &&
                (this.habitacion.getEstado() == estado_habitacion.Disponible
                        || this.habitacion.getEstado() == estado_habitacion.Reservada)) {

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

    public long getNumeroNoches() {
        if (fechaInicio != null && fechaFin != null) {
            return java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        }
        return 0;
    }

    public double calcularSubtotalHabitacion() {
        if (habitacion == null) {
            return 0.0;
        }
        return habitacion.getPrecioPorNoche() * getNumeroNoches();
    }

    public double calcularSubtotalServicios() {
        if (serviciosAdicionales == null || serviciosAdicionales.isEmpty()) {
            return 0.0;
        }
        return serviciosAdicionales.stream().mapToDouble(proyecto_poo.modelo.entidad.serviciosAdicionales::getPrecio).sum();
    }

    public double calcularCostoTotal() {
        return calcularSubtotalHabitacion() + calcularSubtotalServicios();
    }


}
