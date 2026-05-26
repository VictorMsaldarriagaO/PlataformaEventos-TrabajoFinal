package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntradaDecoratorTest {

    private EntradaComponent entradaBase;
    private Asiento asiento;

    @BeforeEach
    public void setUp() {
        Zona zona = new Zona("Z1", "General", 100, 50000.0);
        asiento = new Asiento("A1", "Fila A", 1);
        entradaBase = new BaseEntrada("E1", zona, asiento);
    }

    @Test
    public void testVIPDecorator() {
        EntradaComponent vip = new VIPDecorator(entradaBase, 30000.0);
        assertEquals(80000.0, vip.getPrecioFinal());
        assertTrue(vip.getDetalles().contains("VIP"));
        assertEquals(asiento, vip.getAsiento()); // El asiento no se debe ocultar
    }

    @Test
    public void testVIPYSeguroDecorator() {
        EntradaComponent completa = new SeguroDecorator(new VIPDecorator(entradaBase, 30000.0), 10000.0);
        assertEquals(90000.0, completa.getPrecioFinal());
        assertTrue(completa.getDetalles().contains("Seguro"));
    }
}