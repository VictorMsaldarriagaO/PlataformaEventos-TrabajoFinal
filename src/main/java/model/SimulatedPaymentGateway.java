package model;

public class SimulatedPaymentGateway {
    public boolean authorizePayment(String card, double total) {
        System.out.println("[SISTEMA EXTERNO] Verificando fondos para la tarjeta: " + card);
        System.out.println("[SISTEMA EXTERNO] Cobro autorizado por un valor de: $" + total);
        return true;
    }
}