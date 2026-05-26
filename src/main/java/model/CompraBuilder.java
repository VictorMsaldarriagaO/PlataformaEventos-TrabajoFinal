package model;

import java.util.UUID;

public class CompraBuilder {
    private Compra compra;
    private Usuario usuario;
    private Evento evento;

    public CompraBuilder setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public CompraBuilder setEvento(Evento evento) {
        this.evento = evento;
        return this;
    }

    public CompraBuilder addEntrada(Zona zona, Asiento asiento) {
        if (this.compra == null) {
            if (this.usuario == null || this.evento == null) {
                throw new IllegalStateException("Debe asignar un Usuario y un Evento antes de añadir entradas.");
            }
            String idGenerado = "COMP-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
            this.compra = new Compra(idGenerado, this.usuario, this.evento);
        }

        EntradaComponent nuevaEntrada = new BaseEntrada("ENT-" + System.currentTimeMillis(), zona, asiento);
        this.compra.agregarEntrada(nuevaEntrada);
        return this;
    }

    public Compra build() {
        if (this.compra == null) {
            throw new IllegalStateException("No se puede crear una compra vacía sin entradas.");
        }
        return this.compra;
    }
}