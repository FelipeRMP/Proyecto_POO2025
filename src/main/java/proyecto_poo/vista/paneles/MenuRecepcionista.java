package proyecto_poo.vista.paneles;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.entidad.estado_habitacion;
import proyecto_poo.vista.paneles_secundarios.*;

import javax.swing.*;
import java.awt.*;

public class MenuRecepcionista extends JFrame {
    private ControladorMain controlador;
    private JPanel habitaciones; // Hacerlo variable de instancia

    public MenuRecepcionista(ControladorMain controlador) {
        this.controlador = controlador;

        // ... (código de configuración de la ventana)
        setTitle("Menú");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Sistema de Gestion Hotelera", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        // ...
        /* A. PESTANA EDITAR
        JPanel editar = new JPanel(new GridLayout(2, 1, 10, 10));
        editar.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));
        JButton agregarEmpleadoBtn = new JButton("Agregar Empleado");
        JButton modificarEmpleadoBtn = new JButton("Modificar Empleado");
        agregarEmpleadoBtn.addActionListener(e -> new agregar_empleado(controlador).setVisible(true));
        modificarEmpleadoBtn.addActionListener(e -> new modificar_empleado(controlador).setVisible(true));
        editar.add(agregarEmpleadoBtn);
        editar.add(modificarEmpleadoBtn); */

        // B. PESTANA RESERVA
        JPanel reserva = new JPanel(new GridLayout(2, 1, 10, 10));
        reserva.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));
        JButton Crear = new JButton("Crear reserva");
        JButton Modificar_reserva = new JButton("Buscar y Modificar Reserva");
        Crear.addActionListener(e -> new crear_reserva(controlador, () -> actualizarVistaHabitaciones()).setVisible(true));
        Modificar_reserva.addActionListener(e -> new modificar_reserva(controlador).setVisible(true));
        reserva.add(Crear);
        reserva.add(Modificar_reserva);

        // C. PESTANA CHECK IN/OUT
        JPanel check = new JPanel(new GridLayout(2, 1, 10, 10));
        check.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));
        JButton check_in_btn = new JButton("Check In");
        JButton check_out_btn = new JButton("Check Out");
        check_in_btn.addActionListener(e -> new check_in(controlador).setVisible(true));
        check_out_btn.addActionListener(e -> new check_out(controlador).setVisible(true));
        check.add(check_in_btn);
        check.add(check_out_btn);

        // D. PESTANA HABITACIONES (Matriz visual)
        habitaciones = new JPanel(new GridLayout(4, 4, 5, 5));
        inicializarVistaHabitaciones(); // Llama al método para crear los botones

        // 3. Agregar paneles al JTabbedPane
        //tabs.addTab("Editar", editar);
        tabs.addTab("Reserva", reserva);
        tabs.addTab("Check in/out", check);
        tabs.addTab("Habitaciones y Servicios", habitaciones);

        // --- ChangeListener para actualizar la vista de habitaciones ---
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() == 3) { // El índice 3 es "Habitaciones Disponibles"
                actualizarVistaHabitaciones();
            }
        });

        // 4. Agregar el JTabbedPane a la ventana (Centro)
        add(tabs, BorderLayout.CENTER);

        // Actualización inicial
        actualizarVistaHabitaciones();
    }

    private void inicializarVistaHabitaciones() {
        habitaciones.removeAll(); // Limpia el panel por si se llama varias veces
        for (int piso = 1; piso <= 4; piso++) {
            for (int numero = 1; numero <= 4; numero++) {
                int numHabitacion = (piso * 100) + numero;
                JButton btn = new JButton("Hab " + numHabitacion);
                btn.setName(String.valueOf(numHabitacion)); // Nombre para identificarlo
                habitaciones.add(btn);
            }
        }
        habitaciones.revalidate();
        habitaciones.repaint();
    }

    private void actualizarVistaHabitaciones() {
        if (controlador == null || controlador.getDb() == null) return;

        java.util.List<proyecto_poo.modelo.entidad.habitacion> listaHabitaciones = controlador.getDb().getHabitaciones();

        for (Component comp : habitaciones.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;

                // Limpiar listeners antiguos para evitar duplicados
                for (java.awt.event.ActionListener al : btn.getActionListeners()) {
                    btn.removeActionListener(al);
                }

                try {
                    int numHabitacion = Integer.parseInt(btn.getName());
                    proyecto_poo.modelo.entidad.habitacion hab = listaHabitaciones.stream()
                            .filter(h -> h.getNumero() == numHabitacion)
                            .findFirst()
                            .orElse(null);

                    if (hab != null) {
                        switch (hab.getEstado()) {
                            case Disponible:
                                btn.setBackground(new Color(76, 175, 80)); // Verde
                                break;
                            case Ocupada:
                                btn.setBackground(new Color(244, 67, 54)); // Rojo
                                break;
                            case Reservada:
                                btn.setBackground(new Color(33, 150, 243)); // Azul
                                break;
                            case En_Limpieza:
                                btn.setBackground(new Color(255, 235, 59)); // Amarillo
                                break;
                        }

                        // Añadir ActionListener para la funcionalidad de agregar servicios
                        btn.addActionListener(e -> {
                            if (hab.getEstado() == estado_habitacion.Ocupada) {
                                proyecto_poo.modelo.entidad.reserva reservaActiva = controlador.getDb().getReservaActivaPorHabitacion(numHabitacion);
                                if (reservaActiva != null) {
                                    // Crear un panel para el dialogo
                                    JTextField nombreServicioField = new JTextField();
                                    JTextField precioServicioField = new JTextField();
                                    JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
                                    panel.add(new JLabel("Nombre del Servicio:"));
                                    panel.add(nombreServicioField);
                                    panel.add(new JLabel("Precio:"));
                                    panel.add(precioServicioField);

                                    int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Servicio Adicional",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                    if (result == JOptionPane.OK_OPTION) {
                                        String nombre = nombreServicioField.getText();
                                        String precioStr = precioServicioField.getText();
                                        if (nombre.isEmpty() || precioStr.isEmpty()) {
                                            JOptionPane.showMessageDialog(this, "El nombre y el precio no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                                            return;
                                        }

                                        try {
                                            double precio = Double.parseDouble(precioStr);
                                            proyecto_poo.modelo.entidad.serviciosAdicionales nuevoServicio =
                                                    new proyecto_poo.modelo.entidad.serviciosAdicionales(nombre, precio);
                                            reservaActiva.agregarServicioAdicional(nuevoServicio);

                                            // Opcional: Guardar el estado de la reserva actualizada si es necesario
                                            // controlador.getDb().actualizarReserva(reservaActiva);

                                            JOptionPane.showMessageDialog(this, "Servicio '" + nombre + "' agregado a la habitación " + numHabitacion + ".",
                                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(this, "Por favor, ingrese un precio válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "No se encontró una reserva activa para esta habitación ocupada.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                    }
                } catch (NumberFormatException ex) {
                    // Ignorar botones que no tengan un número como nombre
                }
            }
        }
    }
}