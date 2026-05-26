package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentAdapterTest {

    @Test
    public void testProcesarPagoExitoso() {
        PaymentAdapter adapter = new PaymentAdapterImpl(new SimulatedPaymentGateway());
        MetodoPagoSimulado metodo = new MetodoPagoSimulado("Tarjeta", "1111222233334444", "10/26", "999");

        boolean resultado = adapter.procesarPayment(metodo, 100000.0);
        assertTrue(resultado);
    }
}