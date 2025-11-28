package proyecto_poo.vista.paneles;

import javax.swing.SwingUtilities;

public class app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Esto creará y mostrará tu ventana de login
            Login ventanaLogin = new Login();
            ventanaLogin.setVisible(true);
        });
    }
}