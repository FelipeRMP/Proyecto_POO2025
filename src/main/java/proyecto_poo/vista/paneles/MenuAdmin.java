package proyecto_poo.vista.paneles;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.vista.paneles_secundarios.*;

import javax.swing.*;
import java.awt.*;

public class MenuAdmin extends JFrame {
    private ControladorMain controlador;

    public MenuAdmin(ControladorMain controlador) {


        // 1. Configuración de la Ventana
        setTitle("Menú del Administrador");
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
        JPanel editar = new JPanel(new GridLayout(3, 1, 10, 10));
        // Un margen interno para que los botones no toquen los bordes
        editar.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JButton Borrar = new JButton("Borrar Empleado");
        JButton Agregar = new JButton("Agregar Empleado");
        JButton Modificar = new JButton("Modificar Empleado");

        Borrar.addActionListener(e -> {
            new borrar_empleados().setVisible(true);
        });

        Agregar.addActionListener(e -> {
            new agregar_empleados().setVisible(true);
        });

        Modificar.addActionListener(e -> {
            new modificar_empleados().setVisible(true);
        });


        editar.add(Borrar);
        editar.add(Agregar);
        editar.add(Modificar);







        // B. PESTANA RESERVA
        JPanel reserva = new JPanel(new GridLayout(3, 1, 10, 10));
        reserva.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JButton Buscar = new JButton("Buscar Reserva");
        JButton Crear = new JButton("Crear reserva");
        JButton Modificar_reserva = new JButton("Modificar Reserva");

        Buscar.addActionListener(e -> {
            new buscar_reserva().setVisible(true);
        });

        Crear.addActionListener(e -> {
            new crear_reserva(controlador).setVisible(true);
        });

        Modificar_reserva.addActionListener(e -> {
            new modificar_reserva().setVisible(true);
        });

        reserva.add(Buscar);
        reserva.add(Crear);
        reserva.add(Modificar_reserva);





        // C. PESTANA CHECK IN
        JPanel check = new JPanel(new GridLayout(2, 1, 10, 10));
        check.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JButton check_in_btn = new JButton("Check In");
        JButton check_out_btn = new JButton("Check Out");

        check_in_btn.addActionListener(e -> {
            new check_in().setVisible(true);
        });

        check_out_btn.addActionListener(e -> {
            new check_out().setVisible(true);
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

        // 4. Agregar el JTabbedPane a la ventana (Centro)
        add(tabs, BorderLayout.CENTER);
    }


}