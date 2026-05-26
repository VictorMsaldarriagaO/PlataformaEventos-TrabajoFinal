package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecintoTest {

    @Test
    public void testAgregarZonaARecinto() {
        Recinto recinto = new Recinto("R1", "Estadio", "Calle 1", "Armenia");
        Zona zona = new Zona("Z1", "General", 100, 50000.0);

        recinto.agregarZona(zona);

        assertEquals(1, recinto.getZonas().size(), "El recinto debería tener una zona registrada.");
        assertEquals("General", recinto.getZonas().get(0).getNombre());
    }
}