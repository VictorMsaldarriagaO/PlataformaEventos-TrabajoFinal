package model;

/**
 * Decorador concreto que inyecta el valor de acceso a amenidades VIP.
 */
public class VIPDecorator extends EntradaDecorator {
    private double costoVIP;

    public VIPDecorator(EntradaComponent entrada, double costoVIP) {
        super(entrada);
        this.costoVIP = costoVIP;
    }

    @Override
    public double getPrecioFinal() { return super.getPrecioFinal() + costoVIP; }

    @Override
    public String getDetalles() { return super.getDetalles() + " + Acceso VIP"; }
}