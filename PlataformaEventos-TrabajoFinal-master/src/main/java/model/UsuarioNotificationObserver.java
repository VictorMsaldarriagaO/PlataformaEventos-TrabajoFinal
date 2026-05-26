package model;
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