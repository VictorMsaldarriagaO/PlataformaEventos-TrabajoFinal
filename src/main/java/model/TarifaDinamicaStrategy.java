package model;

/**
 * Estrategia de precio dinámico basado en factores externos como picos de alta demanda.
 */
public class TarifaDinamicaStrategy implements PricingStrategy {
    private double factorMultiplicador;

    public TarifaDinamicaStrategy(double factorMultiplicador) {
        this.factorMultiplicador = factorMultiplicador;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase * factorMultiplicador;
    }
}