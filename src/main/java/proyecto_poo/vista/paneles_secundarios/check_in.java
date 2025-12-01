package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.entidad.estado_habitacion;
import proyecto_poo.modelo.entidad.reserva;

import javax.swing.*;
import java.awt.*;

public class check_in extends JFrame {

    private ControladorMain controlador;
    private JTextField dni_field;
    private JButton checkin_button;
    private Runnable onCheckInSuccess;

    public check_in(ControladorMain controlador, Runnable onCheckInSuccess) {
        this.controlador = controlador;
        this.onCheckInSuccess = onCheckInSuccess;

        setTitle("Sistema de Gestión Hotelera - Check In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel_principal = new JPanel(new BorderLayout(10, 10));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REALIZAR CHECK-IN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel_principal.add(titulo, BorderLayout.NORTH);

        // Panel central para el formulario
        JPanel panel_central = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta DNI
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        panel_central.add(new JLabel("DNI del Huésped:"), gbc);

        // Campo de texto DNI
        dni_field = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        panel_central.add(dni_field, gbc);

        // Botón Check-In
        checkin_button = new JButton("Confirmar Check-In");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.anchor = GridBagConstraints.CENTER;
        panel_central.add(checkin_button, gbc);
        
        panel_principal.add(panel_central, BorderLayout.CENTER);

        // --- ActionListener ---
        checkin_button.addActionListener(e -> realizarCheckIn());

        add(panel_principal);
    }

    private void realizarCheckIn() {
        String dniTexto = dni_field.getText().trim();
        if (dniTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el DNI del huésped.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int dni = Integer.parseInt(dniTexto);
            reserva reserva = controlador.getDb().buscarReservaPorDniHuesped(dni);

            if (reserva == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna reserva para el DNI proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (reserva.getCheckIn()) {
                JOptionPane.showMessageDialog(this, "El huésped ya ha realizado el check-in previamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (reserva.getHabitacion().getEstado() == estado_habitacion.En_Limpieza ||
                    reserva.getHabitacion().getEstado() == estado_habitacion.Ocupada) {
                JOptionPane.showMessageDialog(this, "La habitación " + reserva.getHabitacion().getNumero() + " no está disponible en este momento. Estado actual: " + reserva.getHabitacion().getEstado(), "Error de Habitación", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si todo está correcto, realizamos el check-in
                reserva.checkIn();
                JOptionPane.showMessageDialog(this, "¡Check-in realizado con éxito!\nHabitación: " + reserva.getHabitacion().getNumero(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                onCheckInSuccess.run();
                this.dispose();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI válido (solo números).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}
