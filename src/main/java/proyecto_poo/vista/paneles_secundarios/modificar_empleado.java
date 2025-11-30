package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;

import javax.swing.*;
import java.awt.*;

public class modificar_empleado extends JFrame {

    private ControladorMain controlador;

    public modificar_empleado(ControladorMain controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());

        setTitle("Sistema de Gestión Hotelera - Modificar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250); // Adjusted size
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel titulo = new JLabel("MODIFICAR EMPLEADO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);
    }
}
