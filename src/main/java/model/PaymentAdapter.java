package model;

public interface PaymentAdapter {
    boolean procesarPayment(MetodoPagoSimulado metodo, double monto);
}