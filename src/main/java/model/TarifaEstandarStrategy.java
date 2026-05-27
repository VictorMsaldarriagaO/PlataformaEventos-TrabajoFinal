package model;

/**
 * Estrategia de precio plano sin modificaciones comerciales de recargo.
 */
public class TarifaEstandarStrategy implements PricingStrategy {
    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase;
    }
}