package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.baseDeDatos;
import proyecto_poo.modelo.usuario.tipo_usuario;

import javax.swing.*;
import java.awt.*;

public class agregar_empleados extends JFrame {

    private final baseDeDatos baseDatos;
    private final JTextField campoUsuario = new JTextField();
    private final JPasswordField campoClave = new JPasswordField();
    private final JComboBox<tipo_usuario> comboTipo = new JComboBox<>(tipo_usuario.values());
    private final JButton botonGuardar = new JButton("Guardar");

    public agregar_empleados(baseDeDatos baseDatos) {
        this.baseDatos = baseDatos == null ? new baseDeDatos() : baseDatos;
        configurarVentana();
        construirFormulario();
        setVisible(!GraphicsEnvironment.isHeadless());
    }

    public agregar_empleados() {
        this(null);
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión Hotelera - Agregar Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void construirFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("AGREGAR EMPLEADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);

        panel.add(Box.createVerticalStrut(15));
        panel.add(crearFilaCampo("Nombre de usuario", campoUsuario));
        panel.add(Box.createVerticalStrut(10));
        panel.add(crearFilaCampo("Contraseña", campoClave));
        panel.add(Box.createVerticalStrut(10));
        panel.add(crearFilaCampo("Tipo de usuario", comboTipo));

        panel.add(Box.createVerticalStrut(15));
        botonGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonGuardar.addActionListener(e -> guardarEmpleadoDesdeFormulario());
        panel.add(botonGuardar);

        add(panel);
    }

    private JPanel crearFilaCampo(String etiqueta, JComponent campo) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        fila.add(new JLabel(etiqueta), BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        return fila;
    }

    public boolean guardarEmpleadoDesdeFormulario() {
        String usuario = campoUsuario.getText();
        String clave = new String(campoClave.getPassword());
        tipo_usuario tipoSeleccionado = (tipo_usuario) comboTipo.getSelectedItem();

        try {
            baseDatos.agregarEmpleado(usuario, clave, tipoSeleccionado);
            mostrarMensaje("Empleado guardado correctamente", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            return true;
        } catch (IllegalArgumentException ex) {
            mostrarMensaje(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void limpiarCampos() {
        campoUsuario.setText("");
        campoClave.setText("");
        comboTipo.setSelectedIndex(0);
    }

    private void mostrarMensaje(String mensaje, int tipoMensaje) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println(mensaje);
            return;
        }
        JOptionPane.showMessageDialog(this, mensaje, "Agregar empleado", tipoMensaje);
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoClave() {
        return campoClave;
    }

    public JComboBox<tipo_usuario> getComboTipo() {
        return comboTipo;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }
}
