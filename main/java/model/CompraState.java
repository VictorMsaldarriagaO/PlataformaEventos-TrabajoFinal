package model;

/**
 * Interfaz base del patrón de comportamiento State para el ciclo de vida de la transacción.
 */
public interface CompraState {
    void pagar(Compra compra);
    void cancelar(Compra compra);
}