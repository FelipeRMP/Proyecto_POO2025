package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.entidad.reserva;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class modificar_reserva extends JFrame {

    private final ControladorMain controlador;
    private reserva reserva_actual;

    // Componentes de Búsqueda
    private JTextField dni_busqueda_field;
    private JButton boton_buscar;

    // Componentes del Formulario de Modificación
    private JPanel form_panel;
    private JTextField nombre_field;
    private JTextField apellidos_field;
    private JTextField correo_field;
    private JTextField telefono_field;
    private JTextField fecha_inicio_field;
    private JTextField fecha_fin_field;
    private JLabel dni_label;
    private JLabel habitacion_label;
    private JButton boton_guardar;

    public modificar_reserva(ControladorMain controlador) {
        this.controlador = controlador;

        setTitle("Sistema de Gestión Hotelera - Buscar y Modificar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel_principal = new JPanel(new BorderLayout(10, 10));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        JPanel panel_busqueda = new JPanel(new BorderLayout(10, 10));
        panel_busqueda.setBorder(BorderFactory.createTitledBorder("Buscar Reserva por DNI"));
        dni_busqueda_field = new JTextField();
        boton_buscar = new JButton("Buscar");
        panel_busqueda.add(new JLabel("DNI del Huésped:"), BorderLayout.WEST);
        panel_busqueda.add(dni_busqueda_field, BorderLayout.CENTER);
        panel_busqueda.add(boton_buscar, BorderLayout.EAST);

        form_panel = new JPanel(new GridLayout(8, 2, 5, 5));
        form_panel.setBorder(BorderFactory.createTitledBorder("Datos de la Reserva"));
        
        nombre_field = new JTextField();
        apellidos_field = new JTextField();
        correo_field = new JTextField();
        telefono_field = new JTextField();
        fecha_inicio_field = new JTextField();
        fecha_fin_field = new JTextField();
        dni_label = new JLabel();
        habitacion_label = new JLabel();

        form_panel.add(new JLabel("DNI:"));
        form_panel.add(dni_label);
        form_panel.add(new JLabel("Habitación:"));
        form_panel.add(habitacion_label);
        form_panel.add(new JLabel("Nombre(s):"));
        form_panel.add(nombre_field);
        form_panel.add(new JLabel("Apellidos:"));
        form_panel.add(apellidos_field);
        form_panel.add(new JLabel("Correo:"));
        form_panel.add(correo_field);
        form_panel.add(new JLabel("Teléfono:"));
        form_panel.add(telefono_field);
        form_panel.add(new JLabel("Fecha Check-In (yyyy-MM-dd):"));
        form_panel.add(fecha_inicio_field);
        form_panel.add(new JLabel("Fecha Check-Out (yyyy-MM-dd):"));
        form_panel.add(fecha_fin_field);

        // --- Panel de Botón Guardar (Abajo) ---
        JPanel panel_sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        boton_guardar = new JButton("Guardar Cambios");
        panel_sur.add(boton_guardar);

        // Añadir paneles al principal
        panel_principal.add(panel_busqueda, BorderLayout.NORTH);
        panel_principal.add(form_panel, BorderLayout.CENTER);
        panel_principal.add(panel_sur, BorderLayout.SOUTH);
        
        // Estado inicial: formulario deshabilitado
        setFormularioHabilitado(false);

        // Action Listeners
        boton_buscar.addActionListener(e -> buscarReserva());
        boton_guardar.addActionListener(e -> guardarCambios());

        add(panel_principal);
        setVisible(true);
    }

    private void setFormularioHabilitado(boolean habilitado) {
        for (Component comp : form_panel.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setEditable(habilitado);
            }
        }
        boton_guardar.setEnabled(habilitado);
    }

    private void buscarReserva() {
        try {
            int dni = Integer.parseInt(dni_busqueda_field.getText());
            reserva_actual = controlador.getDb().buscarReservaPorDniHuesped(dni);

            if (reserva_actual != null) {
                llenarDatos();
                setFormularioHabilitado(true);
                JOptionPane.showMessageDialog(this, "Reserva encontrada. Puede modificar los datos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                limpiarDatos();
                setFormularioHabilitado(false);
                JOptionPane.showMessageDialog(this, "No se encontró ninguna reserva para el DNI proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            limpiarDatos();
            setFormularioHabilitado(false);
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarDatos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        dni_label.setText(String.valueOf(reserva_actual.getHuesped().getDni()));
        habitacion_label.setText(String.valueOf(reserva_actual.getHabitacion().getNumero()));
        
        nombre_field.setText(reserva_actual.getHuesped().getNombres());
        apellidos_field.setText(reserva_actual.getHuesped().getApellidos());
        correo_field.setText(reserva_actual.getHuesped().getCorreo());
        telefono_field.setText(String.valueOf(reserva_actual.getHuesped().getTelefono()));
        fecha_inicio_field.setText(reserva_actual.getFechaInicio().format(formatter));
        fecha_fin_field.setText(reserva_actual.getFechaFin().format(formatter));
    }

    private void limpiarDatos() {
        reserva_actual = null;
        dni_label.setText("");
        habitacion_label.setText("");
        nombre_field.setText("");
        apellidos_field.setText("");
        correo_field.setText("");
        telefono_field.setText("");
        fecha_inicio_field.setText("");
        fecha_fin_field.setText("");
    }

    private void guardarCambios() {
        try {
            // Actualizar el objeto huesped anidado
            reserva_actual.getHuesped().setNombres(nombre_field.getText());
            reserva_actual.getHuesped().setApellidos(apellidos_field.getText());
            reserva_actual.getHuesped().setCorreo(correo_field.getText());
            reserva_actual.getHuesped().setTelefono(Integer.parseInt(telefono_field.getText()));

            // Actualizar el objeto reserva
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            reserva_actual.setFechaInicio(LocalDate.parse(fecha_inicio_field.getText(), formatter));
            reserva_actual.setFechaFin(LocalDate.parse(fecha_fin_field.getText(), formatter));

            JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de teléfono válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use yyyy-MM-dd.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}