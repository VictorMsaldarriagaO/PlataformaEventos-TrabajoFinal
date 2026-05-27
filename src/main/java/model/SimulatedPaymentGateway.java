package model;

/**
 * API externa ficticia de la pasarela bancaria. (Clase que requiere ser adaptada).
 */
public class SimulatedPaymentGateway {
    public boolean authorizePayment(String card, double total) {
        System.out.println("[SISTEMA EXTERNO] Verificando fondos para la tarjeta: " + card);
        System.out.println("[SISTEMA EXTERNO] Cobro autorizado por un valor de: $" + total);
        return true;
    }
}