package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.entidad.reserva;

import javax.swing.*;
import java.awt.*;

public class check_out extends JFrame {

    private ControladorMain controlador;
    private JTextField dni_field;
    private JButton checkout_button;

    public check_out(ControladorMain controlador) {
        this.controlador = controlador;

        setTitle("Sistema de Gestión Hotelera - Check Out");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel_principal = new JPanel(new BorderLayout(10, 10));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REALIZAR CHECK-OUT", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel_principal.add(titulo, BorderLayout.NORTH);

        // Panel central para el formulario
        JPanel panel_central = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
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

        // Botón Check-Out
        checkout_button = new JButton("Confirmar Check-Out");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel_central.add(checkout_button, gbc);
        
        panel_principal.add(panel_central, BorderLayout.CENTER);

        // --- ActionListener ---
        checkout_button.addActionListener(e -> realizarCheckOut());

        add(panel_principal);
    }

    private void realizarCheckOut() {
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
            } else if (!reserva.getCheckIn()) {
                JOptionPane.showMessageDialog(this, "El huésped no ha realizado el check-in, por lo que no puede hacer check-out.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                // Calcular costos
                long noches = reserva.getNumeroNoches();
                double precioNoche = reserva.getHabitacion().getPrecioPorNoche();
                double subtotalHabitacion = reserva.calcularSubtotalHabitacion();
                double subtotalServicios = reserva.calcularSubtotalServicios();
                double total = reserva.calcularCostoTotal();

                // Construir el resumen
                StringBuilder resumen = new StringBuilder();
                resumen.append("--- Resumen de Costos ---\n\n");
                resumen.append("Huésped: ").append(reserva.getHuesped().getNombres()).append(" ").append(reserva.getHuesped().getApellidos()).append("\n");
                resumen.append("Habitación: ").append(reserva.getHabitacion().getNumero()).append("\n\n");
                resumen.append("Estadía:\n");
                resumen.append(String.format(" - Precio por noche: $%.2f\n", precioNoche));
                resumen.append(String.format(" - Número de noches: %d\n", noches));
                resumen.append(String.format(" - Subtotal Habitación: $%.2f\n\n", subtotalHabitacion));

                resumen.append("Servicios Adicionales:\n");
                if (reserva.getServiciosAdicionales().isEmpty()) {
                    resumen.append(" - Ninguno\n");
                } else {
                    for (proyecto_poo.modelo.entidad.serviciosAdicionales servicio : reserva.getServiciosAdicionales()) {
                        resumen.append(String.format(" - %s: $%.2f\n", servicio.getNombre(), servicio.getPrecio()));
                    }
                }
                resumen.append(String.format(" - Subtotal Servicios: $%.2f\n\n", subtotalServicios));
                resumen.append("---------------------------------\n");
                resumen.append(String.format("TOTAL A PAGAR: $%.2f\n", total));

                // Mostrar el dialogo de confirmación
                JTextArea textArea = new JTextArea(resumen.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(350, 250)); // Ajustar tamaño

                int opcion = JOptionPane.showConfirmDialog(this, scrollPane, "Confirmar Check-Out y Pago",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (opcion == JOptionPane.OK_OPTION) {
                    // Si todo está correcto, realizamos el check-out
                    reserva.checkOut();
                    JOptionPane.showMessageDialog(this, "¡Check-out realizado con éxito!\nLa habitación pasará a estado de limpieza.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI válido (solo números).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}
