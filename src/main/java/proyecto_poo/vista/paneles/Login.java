package proyecto_poo.vista.paneles;
import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.usuario.admin;
import proyecto_poo.modelo.usuario.recepcionista;
import proyecto_poo.modelo.usuario.usuario;
import proyecto_poo.vista.paneles_secundarios.crear_reserva;

import javax.swing.*;
import java.awt.*;

// Haz que la clase extienda de JFrame para que sea una ventana
public class Login extends JFrame {

    private ControladorMain controlador;
    private JTextField user_input;
    private JPasswordField pass_input; // Mejor usar JPasswordField para contraseñas

    public Login() {
        this.controlador = new ControladorMain(); // Instancia el controlador

        // --- Configuración de la ventana ---
        setTitle("Sistema de Gestión Hotelera - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setResizable(false);

        // --- Creación de componentes ---
        JPanel panelPrincipal = new JPanel(new GridLayout(4, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel textoLogin = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        textoLogin.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        user_input = new JTextField();
        pass_input = new JPasswordField();
        panelCampos.add(new JLabel("Usuario:"));
        panelCampos.add(user_input);
        panelCampos.add(new JLabel("Contraseña:"));
        panelCampos.add(pass_input);

        JButton botonLogin = new JButton("Iniciar Sesión");
        JPanel blanco = new JPanel();

        // --- Añadir componentes al panel principal ---
        panelPrincipal.add(textoLogin);
        panelPrincipal.add(panelCampos);
        panelPrincipal.add(blanco);
        panelPrincipal.add(botonLogin);

        // --- Acción del botón ---
        botonLogin.addActionListener(e -> {
            String user = user_input.getText();
            String pass = new String(pass_input.getPassword());

            // Llama al controlador para que haga la validación, ahora devuelve un objeto usuario
            usuario usuarioAutenticado = controlador.autenticarUsuario(user, pass);

            /*
            Sugerencia: Usar early returns. En lugar de comprobar que NO sea nulo, comprobamos que SI lo sea.
            Asi evitamos el nesting innecesario.
            ATT: Alan
            if (usuarioAutenticado == null) {
                System.out.println("Error de input");
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);

            }

            this.dispose();

            // Comprueba el tipo de usuario y abre la ventana correspondiente
            if (usuarioAutenticado instanceof admin) {
                JOptionPane.showMessageDialog(null, "¡Login de Administrador exitoso!");
                new MenuAdmin(controlador).setVisible(true);
                System.out.println("Login existoso - admin");
            } else if (usuarioAutenticado instanceof recepcionista) {
                JOptionPane.showMessageDialog(null, "¡Login de Recepcionista exitoso!");
                new MenuRecepcionista().setVisible(true);
                System.out.println("Login existoso - recepcionista");
            */


            // Comprueba si el usuario es nulo (fallo en el login)
            if (usuarioAutenticado != null) {
                // Cierra la ventana de login
                this.dispose();

                // Comprueba el tipo de usuario y abre la ventana correspondiente
                if (usuarioAutenticado instanceof admin) {
                    JOptionPane.showMessageDialog(null, "¡Login de Administrador exitoso!");
                    new MenuAdmin(controlador).setVisible(true);
                    System.out.println("Login existoso - admin");
                } else if (usuarioAutenticado instanceof recepcionista) {
                    JOptionPane.showMessageDialog(null, "¡Login de Recepcionista exitoso!");
                    new MenuRecepcionista(controlador).setVisible(true);
                    System.out.println("Login existoso - recepcionista");
                }
            } else {
                // Si el usuario es nulo, muestra error
                System.out.println("Error de input");
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);

            }


        });


        // --- Añadir panel principal a la ventana ---
        add(panelPrincipal);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

}