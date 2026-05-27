package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de contexto transaccional que gestiona el patrón State y almacena tiquetes.
 */
public class Compra {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private List<EntradaComponent> entradas;
    private LocalDateTime fechaCompra;
    private CompraState estadoActual;

    public Compra(String idCompra, Usuario usuario, Evento evento) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.evento = evento;
        this.entradas = new ArrayList<>();
        this.fechaCompra = LocalDateTime.now();
        this.estadoActual = new EstadoPendiente();
    }

    public void agregarEntrada(EntradaComponent entrada) {
        this.entradas.add(entrada);
    }

    public double calcularTotalCompra() {
        double total = 0;
        for (EntradaComponent entrada : entradas) {
            total += entrada.getPrecioFinal();
        }
        return total;
    }

    public CompraState getEstadoActual() { return estadoActual; }
    public void setEstadoActual(CompraState estadoActual) { this.estadoActual = estadoActual; }

    public void processPayment() {
        if (this.estadoActual != null) { this.estadoActual.pagar(this); }
    }

    public void cancelPurchase() {
        if (this.estadoActual != null) { this.estadoActual.cancelar(this); }
    }

    public String getIdCompra() { return idCompra; }
    public Usuario getUsuario() { return usuario; }
    public Evento getEvento() { return evento; }
    public List<EntradaComponent> getEntradas() { return entradas; }
}