package proyecto_poo.controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proyecto_poo.modelo.usuario.admin;
import proyecto_poo.modelo.usuario.recepcionista;
import proyecto_poo.modelo.usuario.tipo_usuario;

import static org.junit.jupiter.api.Assertions.*;

class baseDeDatosTest {

    private baseDeDatos baseDatos;

    @BeforeEach
    void setUp() {
        baseDatos = new baseDeDatos();
    }

    @Test
    void agregarAdminSeGuardaEnLista() {
        int tamañoInicial = baseDatos.getListaAdmins().size();

        baseDatos.agregarEmpleado("nuevoAdmin", "clave123", tipo_usuario.ADMINISTRADOR);

        assertEquals(tamañoInicial + 1, baseDatos.getListaAdmins().size());
        admin agregado = baseDatos.getListaAdmins().getLast();
        assertEquals("nuevoAdmin", agregado.getNombre_usuario());
        assertEquals("clave123", agregado.getClave());
        assertEquals(tipo_usuario.ADMINISTRADOR, agregado.getTipo());
    }

    @Test
    void agregarRecepcionistaSeGuardaEnLista() {
        int tamañoInicial = baseDatos.getListaRecepcionistas().size();

        baseDatos.agregarEmpleado("nuevoRecep", "hotel123", tipo_usuario.RECEPCIONISTA);

        assertEquals(tamañoInicial + 1, baseDatos.getListaRecepcionistas().size());
        recepcionista agregado = baseDatos.getListaRecepcionistas().getLast();
        assertEquals("nuevoRecep", agregado.getNombre_usuario());
        assertEquals("hotel123", agregado.getClave());
        assertEquals(tipo_usuario.RECEPCIONISTA, agregado.getTipo());
    }

    @Test
    void nombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                baseDatos.agregarEmpleado(" ", "clave", tipo_usuario.ADMINISTRADOR));
    }

    @Test
    void claveVaciaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                baseDatos.agregarEmpleado("usuario", "", tipo_usuario.ADMINISTRADOR));
    }

    @Test
    void tipoNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                baseDatos.agregarEmpleado("usuario", "clave", null));
    }
}
