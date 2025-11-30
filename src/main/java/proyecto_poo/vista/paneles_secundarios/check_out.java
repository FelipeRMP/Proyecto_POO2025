package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;

import javax.swing.*;
import java.awt.*;

public class check_out extends JFrame {

    private ControladorMain controlador;

    public check_out(ControladorMain controlador) {
        this.controlador = controlador;

        setTitle("Sistema de Gestión Hotelera - Check Out");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("CHECK OUT", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(titulo);
        add(panel);
    }
}
