package proyecto_poo.vista.paneles_secundarios;

import javax.swing.*;
import java.awt.*;

public class borrar_empleados extends JFrame {

    public borrar_empleados(){
        setTitle("Editar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setResizable(false);

        // --- Creación de componentes ---
        JPanel panel_editar = new JPanel(new GridLayout(4, 1, 40, 10));
        panel_editar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel textoLogin = new JLabel("Editar Empleados", SwingConstants.CENTER);
        textoLogin.setFont(new Font("Arial", Font.BOLD, 24));




    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new borrar_empleados().setVisible(true);
        });
    }
}
