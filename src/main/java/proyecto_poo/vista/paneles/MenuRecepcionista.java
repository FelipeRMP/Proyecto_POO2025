package proyecto_poo.vista.paneles;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.vista.paneles_secundarios.*;

import javax.swing.*;
import java.awt.*;

public class MenuRecepcionista extends JFrame {
    private ControladorMain controlador;

    public MenuRecepcionista(ControladorMain controlador) {
        this.controlador = controlador;

        // 1. Configuración de la Ventana
        setTitle("Menú del Recepcionista");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Título (Header)
        JLabel welcomeLabel = new JLabel("Sistema de Gestion Hotelera", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        // Creación de Pestañas
        JTabbedPane tabs = new JTabbedPane();

        // A. PESTANA EDITAR
        JPanel editar = new JPanel(new GridLayout(2, 1, 10, 10));
        // Un margen interno para que los botones no toquen los bordes
        editar.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));

        JButton agregarEmpleadoBtn = new JButton("Agregar Empleado");
        JButton modificarEmpleadoBtn = new JButton("Modificar Empleado");

        editar.add(agregarEmpleadoBtn);
        editar.add(modificarEmpleadoBtn);

        // B. PESTANA RESERVA
        JPanel reserva = new JPanel(new GridLayout(2, 1, 10, 10)); // Cambiado a 2 filas
        reserva.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50)); // Ajustar margen si es necesario

        JButton Crear = new JButton("Crear reserva");
        JButton Modificar_reserva = new JButton("Buscar y Modificar Reserva");

        Crear.addActionListener(e -> {
            new crear_reserva(controlador).setVisible(true);
        });

        Modificar_reserva.addActionListener(e -> {
            // Ahora abre la ventana unificada
            new modificar_reserva(controlador).setVisible(true);
        });

        reserva.add(Crear);
        reserva.add(Modificar_reserva);

        // C. PESTANA CHECK IN
        JPanel check = new JPanel(new GridLayout(2, 1, 10, 10));
        check.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JButton check_in_btn = new JButton("Check In");
        JButton check_out_btn = new JButton("Check Out");

        check_in_btn.addActionListener(e -> {
            new check_in(controlador).setVisible(true);
        });

        check_out_btn.addActionListener(e -> {
            new check_out(controlador).setVisible(true);
        });

        check.add(check_in_btn);
        check.add(check_out_btn);

        // D. PESTANA HABITACIONES (Matriz visual)
        JPanel habitaciones = new JPanel(new GridLayout(4, 4, 5, 5));

        for (int piso = 1; piso <= 4; piso++) {
            for (int numero = 1; numero <= 4; numero++) {

                int numHabitacion = (piso * 100) + numero;
                JButton btn = new JButton("Hab " + numHabitacion);
                btn.setBackground(Color.GREEN);

                // (Opcional) Le damos un nombre interno al componente por si necesitas buscarlo luego
                btn.setName(String.valueOf(numHabitacion));
                habitaciones.add(btn);
            }
        }

        // 3. Agregar paneles al JTabbedPane
        tabs.addTab("Editar", editar);
        tabs.addTab("Reserva", reserva);
        tabs.addTab("Check in/out", check);
        tabs.addTab("Habitaciones Disponibles", habitaciones);

        // Deshabilitar la pestaña de edición para recepcionistas
        tabs.setEnabledAt(0, false);

        // 4. Agregar el JTabbedPane a la ventana (Centro)
        add(tabs, BorderLayout.CENTER);
    }
}
