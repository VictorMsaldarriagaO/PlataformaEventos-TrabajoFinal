package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CompraBuilderTest {

    @Test
    public void testConstruccionExitosa() {
        Usuario usuario = new Usuario("U001", "Jose David", "josedavid@email.com", "3001234567");
        Evento evento = new Evento("E001", "Concierto", "Música", "Desc", "Armenia", LocalDateTime.now().plusDays(10), "Pol", "Reem", null);
        Zona zona = new Zona("Z001", "VIP", 50, 150000.0);
        Asiento asiento = new Asiento("A1", "A", 1);

        CompraBuilder builder = new CompraBuilder();
        Compra compra = builder.setUsuario(usuario)
                .setEvento(evento)
                .addEntrada(zona, asiento)
                .build();

        assertNotNull(compra);
        assertEquals(1, compra.getEntradas().size());
        assertTrue(compra.getIdCompra().startsWith("COMP-"));
    }

    @Test
    public void testErrorSinUsuarioOEvento() {
        CompraBuilder builder = new CompraBuilder();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            builder.addEntrada(new Zona("Z", "N", 10, 10.0), new Asiento("B1", "B", 1));
        });
        assertEquals("Debe asignar un Usuario y un Evento antes de añadir entradas.", exception.getMessage());
    }

    @Test
    public void testErrorConstruirCompraVacia() {
        CompraBuilder builder = new CompraBuilder();
        builder.setUsuario(new Usuario("U", "N", "E", "T")).setEvento(new Evento("E", "N", "T", "D", "L", LocalDateTime.now(), "P", "R", null));
        Exception exception = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("No se puede crear una compra vacía sin entradas.", exception.getMessage());
    }
}