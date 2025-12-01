package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.entidad.habitacion;
import proyecto_poo.modelo.entidad.huesped;
import proyecto_poo.modelo.entidad.reserva;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class crear_reserva extends JFrame {

    private JButton selectedRoomButton = null;
    private ControladorMain controlador;

    public crear_reserva(ControladorMain controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());

        setTitle("Sistema de Gestión Hotelera - Crear Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 700); // Aumentar altura para nuevos campos
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel titulo = new JLabel("CREAR RESERVA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel_principal = new JPanel(new BorderLayout(10, 10));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cambiado a 8 filas para las fechas
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        JTextField nombre_huesped = new JTextField();
        JTextField apellidos_huesped = new JTextField();
        JTextField correo_huesped = new JTextField();
        JTextField dni_huesped = new JTextField();
        JTextField telefono_huesped = new JTextField();
        JTextField fecha_inicio = new JTextField();
        JTextField fecha_fin = new JTextField();

        formPanel.add(new JLabel("Nombre(s):"));
        formPanel.add(nombre_huesped);
        formPanel.add(new JLabel("Apellidos:"));
        formPanel.add(apellidos_huesped);
        formPanel.add(new JLabel("Correo:"));
        formPanel.add(correo_huesped);
        formPanel.add(new JLabel("DNI: "));
        formPanel.add(dni_huesped);
        formPanel.add(new JLabel("Número de Teléfono:"));
        formPanel.add(telefono_huesped);
        formPanel.add(new JLabel("Fecha Check-In (yyyy-MM-dd):"));
        formPanel.add(fecha_inicio);
        formPanel.add(new JLabel("Fecha Check-Out (yyyy-MM-dd):"));
        formPanel.add(fecha_fin);

        panel_principal.add(formPanel, BorderLayout.CENTER);

        JPanel habitacionesPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        habitacionesPanel.setBorder(BorderFactory.createTitledBorder("Seleccionar Habitación"));

        for (habitacion hab : controlador.getDb().getHabitaciones()) {
            JButton btn = new JButton(String.valueOf(hab.getNumero()));
            switch (hab.getEstado()) {
                case Disponible:
                    btn.setBackground(Color.GREEN);
                    break;
                case Ocupada:
                    btn.setBackground(Color.RED);
                    btn.setEnabled(false);
                    break;
                case Reservada:
                    btn.setBackground(new Color(33, 150, 243));
                    btn.setEnabled(false);
                    break;
                case En_Limpieza:
                    btn.setBackground(Color.ORANGE);
                    btn.setEnabled(false);
                    break;
            }

            btn.addActionListener(e -> {
                if (selectedRoomButton != null) {

                }
                selectedRoomButton = (JButton) e.getSource();
                selectedRoomButton.setBackground(Color.CYAN);
            });
            btn.setName(String.valueOf(hab.getNumero()));
            habitacionesPanel.add(btn);
        }

        panel_principal.add(habitacionesPanel, BorderLayout.SOUTH);

        JButton registrar = new JButton("Registrar");

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(registrar);
        add(titulo, BorderLayout.NORTH);
        add(panel_principal, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        registrar.addActionListener(e -> {
            try {
                // v alidar que se seleccionó una habitación
                if (selectedRoomButton == null) {
                    JOptionPane.showMessageDialog(this, "Por favor, seleccione una habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ///// recopilar datos del formulario
                String nombre = nombre_huesped.getText();
                String apellidos = apellidos_huesped.getText();
                String correo = correo_huesped.getText();
                int dni = Integer.parseInt(dni_huesped.getText());
                int telefono = Integer.parseInt(telefono_huesped.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicio = LocalDate.parse(fecha_inicio.getText(), formatter);
                LocalDate fechaFin = LocalDate.parse(fecha_fin.getText(), formatter);

                if (!fechaFin.isAfter(fechaInicio)) {
                    JOptionPane.showMessageDialog(this, "La fecha de check-out debe ser posterior a la de check-in.", "Rango de fechas inválido", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ///// 3. Crear objeto huesped y registrarlo
                huesped nuevoHuesped = new huesped(dni, telefono, nombre, apellidos, correo);
                controlador.getDb().registrarHuesped(dni, telefono, nombre, apellidos, correo);

                // ///4 Obtener el objeto habitacion
                int numHabitacion = Integer.parseInt(selectedRoomButton.getName());
                habitacion habitacionSeleccionada = null;
                for (habitacion hab : controlador.getDb().getHabitaciones()) {
                    if (hab.getNumero() == numHabitacion) {
                        habitacionSeleccionada = hab;
                        break;
                    }
                }

                if (habitacionSeleccionada == null) {
                     JOptionPane.showMessageDialog(this, "Error al encontrar la habitación seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }

                if (habitacionSeleccionada.getEstado() != proyecto_poo.modelo.entidad.estado_habitacion.Disponible) {
                    JOptionPane.showMessageDialog(this, "La habitación seleccionada ya no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 5. Crear la reserva
                reserva nuevaReserva = new reserva(nuevoHuesped, habitacionSeleccionada, fechaInicio, fechaFin, false);

                // Marcar la habitación como reservada para evitar dobles asignaciones
                habitacionSeleccionada.setEstado(proyecto_poo.modelo.entidad.estado_habitacion.Reservada);

                // 6. Guardar la reserva a través del controlador
                controlador.getDb().agregarReserva(nuevaReserva);

                // 7. Confirmación y cerrar ventana
                JOptionPane.showMessageDialog(this, "Reserva creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para DNI y Teléfono.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use yyyy-MM-dd.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

