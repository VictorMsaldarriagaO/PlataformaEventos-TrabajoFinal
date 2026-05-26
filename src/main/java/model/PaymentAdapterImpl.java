package model;

public class PaymentAdapterImpl implements PaymentAdapter {
    private SimulatedPaymentGateway gateway;

    public PaymentAdapterImpl(SimulatedPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public boolean procesarPayment(MetodoPagoSimulado metodo, double monto) {
        System.out.println("[ADAPTADOR] Traduciendo datos al formato externo...");
        return gateway.authorizePayment(metodo.getNumeroTarjeta(), monto);
    }
}