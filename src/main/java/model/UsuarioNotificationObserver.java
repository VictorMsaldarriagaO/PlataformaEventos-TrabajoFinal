package model;

/**
 * Observador concreto que asocia logs en consola para un cliente específico.
 */
public class UsuarioNotificationObserver implements Observer {
    private Usuario usuario;

    public UsuarioNotificationObserver(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void update(String message) {
        System.out.println("🔔 [Notificación para " + usuario.getNombreCompleto() + "]: " + message);
    }
}