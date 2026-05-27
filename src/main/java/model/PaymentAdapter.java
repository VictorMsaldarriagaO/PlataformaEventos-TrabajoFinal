package model;

/**
 * Interfaz objetivo que espera nuestro sistema de compras para procesar pasarelas de pago.
 */
public interface PaymentAdapter {
    boolean procesarPayment(MetodoPagoSimulado metodo, double monto);
}