package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TarifaStrategyTest {
    @Test
    public void testTarifaEstandarStrategy() {
        Zona zona = new Zona("Z1", "General", 100, 50000.0);
        zona.setPricingStrategy(new TarifaEstandarStrategy());
        assertEquals(50000.0, zona.getPrecioCalculado());
    }

    @Test
    public void testTarifaDinamicaStrategy() {
        Zona zona = new Zona("Z2", "VIP", 50, 100000.0);
        zona.setPricingStrategy(new TarifaDinamicaStrategy(1.2));
        assertEquals(120000.0, zona.getPrecioCalculado());
    }
}