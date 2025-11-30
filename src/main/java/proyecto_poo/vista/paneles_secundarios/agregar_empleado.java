package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.usuario.tipo_usuario;
import javax.swing.*;
import java.awt.*;

public class agregar_empleado extends JFrame {

    private ControladorMain controlador;

    public agregar_empleado(ControladorMain controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());

        setTitle("Sistema de Gestión Hotelera - Agregar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250); // Adjusted size
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel titulo = new JLabel("AGREGAR EMPLEADO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panel_principal = new JPanel(new BorderLayout(10, 10));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows for fields
        JTextField nombre_usuario = new JTextField();
        JPasswordField clave = new JPasswordField();
        JComboBox<tipo_usuario> tipo_usuario_combobox = new JComboBox<>(tipo_usuario.values());

        formPanel.add(new JLabel("Nombre de Usuario:"));
        formPanel.add(nombre_usuario);
        formPanel.add(new JLabel("Contraseña:"));
        formPanel.add(clave);
        formPanel.add(new JLabel("Tipo de Usuario:"));
        formPanel.add(tipo_usuario_combobox);

        panel_principal.add(formPanel, BorderLayout.CENTER);

        JButton registrar = new JButton("Registrar");

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(registrar);
        add(titulo, BorderLayout.NORTH);
        add(panel_principal, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        registrar.addActionListener(e -> {
                String nombreUsuario = nombre_usuario.getText();
                String contrasena = new String(clave.getPassword());
                tipo_usuario tipoUsuario = (tipo_usuario) tipo_usuario_combobox.getSelectedItem();

                if (nombreUsuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre de usuario y la contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controlador.getDb().agregarEmpleado(nombreUsuario, contrasena, tipoUsuario);


                JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();


        });

        setVisible(true);
    }
}
