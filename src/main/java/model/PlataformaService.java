package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor central del sistema implementado bajo el patrón Singleton.
 * Mantiene el estado persistente simulado de la cartelera y usuarios en memoria.
 */
public class PlataformaService {
    private static PlataformaService instancia;

    private List<Usuario> usuarios;
    private List<Evento> eventos;
    private List<Compra> compras;
    private Usuario usuarioAutenticado;

    private PlataformaService() {
        this.usuarios = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.compras = new ArrayList<>();
        inicializarDatosDemo();
    }

    public static PlataformaService getInstancia() {
        if (instancia == null) { instancia = new PlataformaService(); }
        return instancia;
    }

    public boolean iniciarSesion(String correo, String telefono) {
        for (Usuario u : usuarios) {
            if (u.getCorreoElectronico().equalsIgnoreCase(correo)) {
                this.usuarioAutenticado = u;
                return true;
            }
        }
        return false;
    }

    public void registrarUsuario(Usuario usuario) { this.usuarios.add(usuario); }
    public void agregarEvento(Evento evento) { this.eventos.add(evento); }
    public List<Evento> getEventos() { return eventos; }
    public Usuario getUsuarioAutenticado() { return usuarioAutenticado; }
    public void registrarCompra(Compra compra) { this.compras.add(compra); }

    public List<Compra> getComprasDelUsuario(String idUsuario) {
        List<Compra> filtradas = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getUsuario().getIdUsuario().equals(idUsuario)) {
                filtradas.add(c);
            }
        }
        return filtradas;
    }

    private void inicializarDatosDemo() {
        Usuario userDemo = new Usuario("U001", "Jose David", "josedavid@email.com", "3001234567");
        userDemo.agregarMetodoPago("Tarjeta de Crédito Visa");
        usuarios.add(userDemo);

        Recinto estadio = new Recinto("R001", "Estadio Centenario", "Calle 10", "Armenia");
        Zona vip = new Zona("Z001", "Zona VIP", 5, 150000.0);
        for (int i = 1; i <= 5; i++) {
            vip.agregarAsiento(new Asiento("A-" + i, "A", i));
        }
        estadio.agregarZona(vip);

        java.time.LocalDateTime fecha = java.time.LocalDateTime.now().plusDays(30);
        Evento eventoDemo = new Evento("E001", "Concierto Filarmónico", "Música",
                "Espectáculo clásico", "Armenia", fecha, "No cancelable", "Reembolso 50%", estadio);
        eventoDemo.setEstadoEvento(EstadoEvento.PUBLICADO);
        eventos.add(eventoDemo);
    }
}