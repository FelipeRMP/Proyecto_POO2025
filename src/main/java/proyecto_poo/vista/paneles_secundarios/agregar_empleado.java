package proyecto_poo.vista.paneles_secundarios;
import javax.swing.*;

public class agregar_empleado extends JFrame {


    public agregar_empleado() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new agregar_empleado().setVisible(true);
        });
    }
}