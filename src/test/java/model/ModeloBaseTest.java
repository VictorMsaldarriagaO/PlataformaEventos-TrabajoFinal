package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModeloBaseTest {

    @Test
    public void testAsientoInicialDisponible() {
        Asiento asiento = new Asiento("A1", "Fila A", 1);
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }

    @Test
    public void testZonaSinEstrategiaDevuelvePrecioBase() {
        Zona zona = new Zona("Z1", "Platea", 50, 80000.0);
        assertEquals(80000.0, zona.getPrecioCalculado());
    }
}