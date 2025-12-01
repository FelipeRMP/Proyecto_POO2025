package proyecto_poo.vista.paneles_secundarios;

import proyecto_poo.controlador.ControladorMain;
import proyecto_poo.modelo.usuario.admin;
import proyecto_poo.modelo.usuario.recepcionista;
import proyecto_poo.modelo.usuario.tipo_usuario;
import proyecto_poo.modelo.usuario.usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modificar_empleado extends JFrame {

    private ControladorMain controlador;
    private usuario empleado_actual; // Almacenará el empleado que se está modificando

    // Componentes de Búsqueda
    private JTextField nombre_usuario_busqueda_field;
    private JButton boton_buscar;

    // Componentes del Formulario de Modificación
    private JPanel form_panel;
    private JTextField nombre_usuario_field;
    private JPasswordField clave_field;
    private JComboBox<tipo_usuario> tipo_usuario_combobox; // Para mostrar/modificar el tipo
    private JButton boton_guardar;

    public modificar_empleado(ControladorMain controlador) {
        this.controlador = controlador;

        setTitle("Sistema de Gestión Hotelera - Modificar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400); // Tamaño ajustado para más contenido
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel_principal = new JPanel(new BorderLayout(10, 10));
        panel_principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- Panel de Búsqueda ---
        JPanel panel_busqueda = new JPanel(new BorderLayout(10, 10));
        panel_busqueda.setBorder(BorderFactory.createTitledBorder("Buscar Empleado por Nombre de Usuario"));
        nombre_usuario_busqueda_field = new JTextField();
        boton_buscar = new JButton("Buscar");
        panel_busqueda.add(new JLabel("Nombre de Usuario:"), BorderLayout.WEST);
        panel_busqueda.add(nombre_usuario_busqueda_field, BorderLayout.CENTER);
        panel_busqueda.add(boton_buscar, BorderLayout.EAST);

        // --- Panel del Formulario de Modificación ---
        form_panel = new JPanel(new GridLayout(3, 2, 5, 5));
        form_panel.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));

        nombre_usuario_field = new JTextField();
        nombre_usuario_field.setEditable(false); // El nombre de usuario no se puede cambiar directamente
        clave_field = new JPasswordField();
        tipo_usuario_combobox = new JComboBox<>(tipo_usuario.values());

        form_panel.add(new JLabel("Nombre de Usuario:"));
        form_panel.add(nombre_usuario_field);
        form_panel.add(new JLabel("Contraseña:"));
        form_panel.add(clave_field);
        form_panel.add(new JLabel("Tipo de Usuario:"));
        form_panel.add(tipo_usuario_combobox);

        // --- Panel de Botón Guardar ---
        JPanel panel_sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        boton_guardar = new JButton("Guardar Cambios");
        panel_sur.add(boton_guardar);

        // Añadir paneles al principal
        panel_principal.add(panel_busqueda, BorderLayout.NORTH);
        panel_principal.add(form_panel, BorderLayout.CENTER);
        panel_principal.add(panel_sur, BorderLayout.SOUTH);

        // Estado inicial: formulario deshabilitado
        setFormularioHabilitado(false);

        //  listeners
        boton_buscar.addActionListener(e -> buscarEmpleado());
        boton_guardar.addActionListener(e -> guardarCambios());

        add(panel_principal);
        setVisible(true);
    }

    private void setFormularioHabilitado(boolean habilitado) {
        nombre_usuario_field.setEditable(false); // Siempre será false para el nombre de usuario
        clave_field.setEditable(habilitado);
        tipo_usuario_combobox.setEnabled(habilitado);
        boton_guardar.setEnabled(habilitado);
    }

    private void limpiarDatos() {
        empleado_actual = null;
        nombre_usuario_field.setText("");
        clave_field.setText("");
        tipo_usuario_combobox.setSelectedItem(tipo_usuario.ADMINISTRADOR); // o algún valor por defecto
    }

    private void llenarDatos(usuario empleado) {
        this.empleado_actual = empleado;
        nombre_usuario_field.setText(empleado.getNombre_usuario());
        clave_field.setText(empleado.getClave()); // La clave debería ser manejada con más seguridad
        tipo_usuario_combobox.setSelectedItem(empleado.getTipo());
    }

    private void buscarEmpleado() {
        String nombreUsuario = nombre_usuario_busqueda_field.getText().trim();
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de usuario para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        empleado_actual = controlador.getDb().buscarEmpleadoPorNombreUsuario(nombreUsuario);

        if (empleado_actual != null) {
            llenarDatos(empleado_actual);
            setFormularioHabilitado(true);
            JOptionPane.showMessageDialog(this, "Empleado encontrado. Puede modificar sus datos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limpiarDatos();
            setFormularioHabilitado(false);
            JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con el nombre de usuario proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        if (empleado_actual == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún empleado cargado para guardar cambios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nuevaClave = new String(clave_field.getPassword()).trim();
        tipo_usuario nuevoTipo = (tipo_usuario) tipo_usuario_combobox.getSelectedItem();

        if (nuevaClave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacía.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // actualizar objeto
        empleado_actual.setClave(nuevaClave);
        empleado_actual.setTipo(nuevoTipo);

        // actualizar cambios
        controlador.getDb().actualizarEmpleado(empleado_actual);

        JOptionPane.showMessageDialog(this, "Empleado actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose(); // Cerrar la ventana después de guardar
    }
}
