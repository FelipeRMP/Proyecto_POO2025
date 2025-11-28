package proyecto_poo.vista.paneles_secundarios;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// IMPORTA TU MODELO REAL
// import proyecto_poo.modelo.reserva;
// import proyecto_poo.modelo.BaseDeDatos;

public class buscar_reserva extends JFrame {

    private JTextField caja_busqueda;
    private JButton boton_parametro;
    private JList<reserva> lista_reservas;
    private DefaultListModel<reserva> modelo_lista;

    private CriterioBusqueda criterioActual = CriterioBusqueda.HUESPED;

    // Simulación de datos (cámbialo por tu BaseDeDatos.reservas)
    private List<reserva> todasLasReservas = new ArrayList<>();

    // Enum para criterio
    private enum CriterioBusqueda {
        HUESPED, HABITACION, NUMERO_RESERVA
    }

    public buscar_reserva() {

        setTitle("Sistema de Gestión Hotelera - Buscar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Cargar datos de prueba (reemplaza por tu BaseDeDatos)
        cargarDatosDemo();

        JPanel buscar_reserva = new JPanel(new BorderLayout(10, 10));
        buscar_reserva.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título
        JLabel texto_buscar_reserva = new JLabel("BUSCAR RESERVA", SwingConstants.CENTER);
        texto_buscar_reserva.setFont(new Font("Arial", Font.BOLD, 22));
        buscar_reserva.add(texto_buscar_reserva, BorderLayout.NORTH);

        // PANEL SUPERIOR: barra de búsqueda estilo explorador
        JPanel barra_busqueda = new JPanel(new BorderLayout(5, 0));

        caja_busqueda = new JTextField();
        caja_busqueda.setToolTipText("Escribe para buscar...");

        // Escuchar cambios para filtrar en vivo
        caja_busqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarLista(); }

            @Override
            public void removeUpdate(DocumentEvent e) { filtrarLista(); }

            @Override
            public void changedUpdate(DocumentEvent e) { filtrarLista(); }
        });

        boton_parametro = new JButton("Huésped ▾"); // texto inicial
        boton_parametro.addActionListener(e -> mostrarMenuCriterios());

        barra_busqueda.add(caja_busqueda, BorderLayout.CENTER);
        barra_busqueda.add(boton_parametro, BorderLayout.EAST);

        buscar_reserva.add(barra_busqueda, BorderLayout.SOUTH);

        // CENTRO: lista de reservas
        modelo_lista = new DefaultListModel<>();
        lista_reservas = new JList<>(modelo_lista);
        lista_reservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista_reservas.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(lista_reservas);
        buscar_reserva.add(scroll, BorderLayout.CENTER);

        // Inicializar lista completa
        filtrarLista();

        add(buscar_reserva);
        setVisible(true);
    }

    // Menú emergente para elegir criterio de búsqueda
    private void mostrarMenuCriterios() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem porHuesped = new JMenuItem("Huésped");
        porHuesped.addActionListener(e -> {
            criterioActual = CriterioBusqueda.HUESPED;
            boton_parametro.setText("Huésped ▾");
            filtrarLista();
        });

        JMenuItem porHabitacion = new JMenuItem("Habitación");
        porHabitacion.addActionListener(e -> {
            criterioActual = CriterioBusqueda.HABITACION;
            boton_parametro.setText("Habitación ▾");
            filtrarLista();
        });

        JMenuItem porNumero = new JMenuItem("N° Reserva");
        porNumero.addActionListener(e -> {
            criterioActual = CriterioBusqueda.NUMERO_RESERVA;
            boton_parametro.setText("N° Reserva ▾");
            filtrarLista();
        });

        menu.add(porHuesped);
        menu.add(porHabitacion);
        menu.add(porNumero);

        // Mostrar debajo del botón
        menu.show(boton_parametro, 0, boton_parametro.getHeight());
    }

    // Filtra la lista según texto y criterio
    private void filtrarLista() {
        String texto = caja_busqueda.getText().trim().toLowerCase();
        modelo_lista.clear();

        for (reserva r : todasLasReservas) {
            if (coincide(r, texto)) {
                modelo_lista.addElement(r);
            }
        }
    }

    // Lógica de comparación según criterio
    private boolean coincide(reserva r, String texto) {
        if (texto.isEmpty()) {
            return true; // sin filtro => muestra todo
        }

        switch (criterioActual) {
            case HUESPED:
                return r.getNombreHuesped().toLowerCase().contains(texto);
            case HABITACION:
                return r.getNumeroHabitacion().toLowerCase().contains(texto);
            case NUMERO_RESERVA:
                return r.getCodigo().toLowerCase().contains(texto);
            default:
                return false;
        }
    }

    // DEMO de datos y clase reserva (ADAPTA esto a tu modelo real)
    private void cargarDatosDemo() {
        todasLasReservas.add(new reserva("R001", "101", "Juan Pérez"));
        todasLasReservas.add(new reserva("R002", "102", "María López"));
        todasLasReservas.add(new reserva("R003", "201", "Carlos Díaz"));
        todasLasReservas.add(new reserva("R004", "202", "Luciana Gómez"));
        todasLasReservas.add(new reserva("R005", "301", "Felipe Madalengoitia"));
    }

    // Clase interna demo: reemplázala por tu modelo real
    public static class reserva {
        private String codigo;
        private String numeroHabitacion;
        private String nombreHuesped;

        public reserva(String codigo, String numeroHabitacion, String nombreHuesped) {
            this.codigo = codigo;
            this.numeroHabitacion = numeroHabitacion;
            this.nombreHuesped = nombreHuesped;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getNumeroHabitacion() {
            return numeroHabitacion;
        }

        public String getNombreHuesped() {
            return nombreHuesped;
        }

        @Override
        public String toString() {
            // Lo que se ve en la lista
            return String.format("Reserva %s | Hab: %s | Huesped: %s",
                    codigo, numeroHabitacion, nombreHuesped);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(buscar_reserva::new);
    }
}
