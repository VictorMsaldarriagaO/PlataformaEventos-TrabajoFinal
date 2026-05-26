package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlataformaServiceTest {

    @Test
    public void testUnicaInstanciaSingleton() {
        PlataformaService instancia1 = PlataformaService.getInstancia();
        PlataformaService instancia2 = PlataformaService.getInstancia();
        assertSame(instancia1, instancia2);
    }
}