package model;
// CompraState.java
public interface CompraState {
    void pagar(Compra compra);
    void cancelar(Compra compra);
}