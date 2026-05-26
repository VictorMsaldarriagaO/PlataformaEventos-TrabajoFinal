package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {

    @Test
    public void testAttachYDetachObserver() {
        NotificationService service = new NotificationService();
        Observer observer = new UsuarioNotificationObserver(new Usuario("U1", "Jose", "jose@email.com", "123"));

        service.attach(observer);
        assertDoesNotThrow(() -> service.notifyObservers("Mensaje de prueba"));

        service.detach(observer);
        assertDoesNotThrow(() -> service.notifyObservers("Mensaje sin observadores"));
    }
}