package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CompraStateTest {

    private Compra compra;
    private Asiento asiento;
    private MetodoPagoSimulado metodoPago;
    private PaymentAdapter adaptador;

    @BeforeEach
    public void setUp() {
        Usuario usuario = new Usuario("U1", "Jose", "jose@email.com", "123");
        Evento evento = new Evento("EV1", "Concierto", "Musica", "Desc", "Lugar", LocalDateTime.now(), "Pol", "Reem", null);
        Zona zona = new Zona("Z1", "General", 100, 50000.0);
        asiento = new Asiento("A1", "Fila A", 1);
        asiento.setEstadoAsiento(EstadoAsiento.RESERVADO);

        compra = new Compra("C1", usuario, evento);
        compra.agregarEntrada(new BaseEntrada("E1", zona, asiento));

        metodoPago = new MetodoPagoSimulado("Tarjeta", "1234", "12/25", "123");
        adaptador = new PaymentAdapterImpl(new SimulatedPaymentGateway());
    }

    @Test
    public void testTransicionPendienteAPagada() {
        boolean exito = compra.procesarPago(metodoPago, adaptador);
        assertTrue(exito);
        assertTrue(compra.getEstadoActual() instanceof EstadoPagada);
        assertEquals(EstadoAsiento.VENDIDO, asiento.getEstadoAsiento());
    }

    @Test
    public void testCancelacionDesdePendienteLiberaAsientos() {
        compra.cancelarCompra();
        assertTrue(compra.getEstadoActual() instanceof EstadoCancelada);
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }

    @Test
    public void testPagarCompraYaPagadaDaError() {
        compra.procesarPago(metodoPago, adaptador);
        boolean segundoIntento = compra.procesarPago(metodoPago, adaptador);
        assertFalse(segundoIntento);
    }
}