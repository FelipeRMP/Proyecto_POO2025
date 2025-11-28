package proyecto_poo.vista.paneles_secundarios;

import javax.swing.*;
import java.awt.*;

public class modificar_reserva extends JFrame {

    public modificar_reserva() {

        setTitle("Sistema de Gestión Hotelera - Modificar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("MODIFICAR RESERVA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(titulo);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new modificar_reserva();
    }
}
