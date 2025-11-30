package proyecto_poo.vista.paneles_secundarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proyecto_poo.controlador.baseDeDatos;
import proyecto_poo.modelo.usuario.tipo_usuario;

import static org.junit.jupiter.api.Assertions.*;

class agregar_empleadosTest {

    private baseDeDatos baseDatos;
    private agregar_empleados vista;

    @BeforeEach
    void setUp() {
        System.setProperty("java.awt.headless", "true");
        baseDatos = new baseDeDatos();
        vista = new agregar_empleados(baseDatos);
    }

    @Test
    void guardaAdminDesdeFormulario() {
        int tamañoInicial = baseDatos.getListaAdmins().size();

        vista.getCampoUsuario().setText("nuevoAdmin");
        vista.getCampoClave().setText("segura123");
        vista.getComboTipo().setSelectedItem(tipo_usuario.ADMINISTRADOR);

        assertTrue(vista.guardarEmpleadoDesdeFormulario());
        assertEquals(tamañoInicial + 1, baseDatos.getListaAdmins().size());
    }

    @Test
    void nombreVacioNoSeGuarda() {
        int tamañoInicial = baseDatos.getListaRecepcionistas().size();

        vista.getCampoUsuario().setText("   ");
        vista.getCampoClave().setText("clave");
        vista.getComboTipo().setSelectedItem(tipo_usuario.RECEPCIONISTA);

        assertFalse(vista.guardarEmpleadoDesdeFormulario());
        assertEquals(tamañoInicial, baseDatos.getListaRecepcionistas().size());
    }
}
