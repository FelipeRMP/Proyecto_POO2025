package proyecto_poo.vista;
import javax.swing.*;
import java.awt.*;

public class menu {

    private void menu() {
        // 1. Crear la ventana (JFrame)
        JFrame frame = new JFrame("Sistema de Gestión Hotelera");

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new GridLayout(2, 1, 10, 10));
        JLabel texto1 = new JLabel("LOGIN", SwingConstants.CENTER);
        JButton boton1 = new JButton("Iniciar sesión");
        JTextField user_input = new JTextField(20);
        JPanel panel2_1 = new JPanel();
        JPanel panel2_2 = new JPanel();


        boton1.addActionListener(e -> {
            String user = user_input.getText();
            System.out.println("Hola " + user);
        });


        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout. CENTER);
        panel1.add(texto1);
        panel2_1.add(user_input);
        panel2_2.add(boton1);
        panel2.add(panel2_1);
        panel2.add(panel2_2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        // Buena práctica: Iniciar la UI en el hilo correcto
        SwingUtilities.invokeLater(() -> {
            new menu().menu();
        });
    }
}