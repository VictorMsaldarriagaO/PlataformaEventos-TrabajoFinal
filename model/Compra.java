package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private List<EntradaComponent> entradas;
    private LocalDateTime fechaCompra;

    // 1. NUEVO: La variable para el Patrón State
    private CompraState estadoActual;

    public Compra(String idCompra, Usuario usuario, Evento evento) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.evento = evento;
        this.entradas = new ArrayList<>();
        this.fechaCompra = LocalDateTime.now();
        // 2. NUEVO: Toda compra nace en estado Pendiente
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

    // 3. NUEVO: Los Getters y Setters que eliminan tu error de compilación
    public CompraState getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(CompraState estadoActual) {
        this.estadoActual = estadoActual;
    }

    // 4. NUEVO: Métodos delegados para facilitar el uso desde el Main
    public void procesarPago() {
        if (this.estadoActual != null) {
            this.estadoActual.pagar(this);
        }
    }

    public void cancelarCompra() {
        if (this.estadoActual != null) {
            this.estadoActual.cancelar(this);
        }
    }

    // Getters originales
    public String getIdCompra() { return idCompra; }
    public Usuario getUsuario() { return usuario; }
    public Evento getEvento() { return evento; }
    public List<EntradaComponent> getEntradas() { return entradas; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
}