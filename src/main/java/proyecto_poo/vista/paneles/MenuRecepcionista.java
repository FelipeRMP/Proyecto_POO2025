package proyecto_poo.vista.paneles;

import proyecto_poo.controlador.ControladorMain;

import javax.swing.*;

public class MenuRecepcionista extends JFrame {
    private ControladorMain controlador;
    public MenuRecepcionista() {
        setTitle("Menú del Recepcionista");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Bienvenido, Recepcionista", SwingConstants.CENTER);
        add(welcomeLabel);
    }
}
