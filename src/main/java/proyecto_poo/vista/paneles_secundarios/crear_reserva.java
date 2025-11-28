package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;

import javax.swing.*;
import java.awt.*;

public class crear_reserva extends JFrame {

    private ControladorMain controlador;
    public crear_reserva(ControladorMain controlador) {
        this.controlador = controlador;

        ControladorMain bd = new ControladorMain();

        setTitle("Sistema de Gestión Hotelera - Crear Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel titulo = new JLabel("CREAR RESERVA");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));


        JPanel panel_principal = new JPanel(new GridLayout(7, 2, 5, 5));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nombre_huesped = new JTextField();
        JTextField apellidos_huesped = new JTextField();
        JTextField correo_huesped = new JTextField();
        JTextField dni_huesped = new JTextField();
        JTextField telefono_huesped = new JTextField();
        JButton registrar = new JButton("Registrar");
        panel_principal.add(new JLabel(""));
        panel_principal.add(new JLabel(""));
        panel_principal.add(new JLabel("Nombre(s):"));
        panel_principal.add(nombre_huesped);
        panel_principal.add(new JLabel("Apellidos:"));
        panel_principal.add(apellidos_huesped);
        panel_principal.add(new JLabel("Correo:"));
        panel_principal.add(correo_huesped);
        panel_principal.add(new JLabel("DNI: "));
        panel_principal.add(dni_huesped);
        panel_principal.add(new JLabel("Numero de Telefono"));
        panel_principal.add(telefono_huesped);
        panel_principal.add(new JLabel(""));
        panel_principal.add(registrar);

        registrar.addActionListener(e -> {
            String nombre = nombre_huesped.getText();
            String apellidos = apellidos_huesped.getText();
            String correo = correo_huesped.getText();
            int dni = Integer.parseInt(dni_huesped.getText());
            int telefono = Integer.parseInt(telefono_huesped.getText());

            controlador.getDb().registrarHuesped(dni, telefono, nombre, apellidos, correo);





        });

       // panel.add(titulo);
        add(panel_principal, SwingConstants.CENTER);

        setVisible(true);
    }


}
