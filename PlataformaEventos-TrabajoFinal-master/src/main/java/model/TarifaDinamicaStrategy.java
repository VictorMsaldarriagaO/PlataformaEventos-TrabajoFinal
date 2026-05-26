package model;
// TarifaDinámicaStrategy.java (Implementación 2: Aumenta por alta demanda)
public class TarifaDinamicaStrategy implements PricingStrategy {
    private double factorMultiplicador;

    public TarifaDinamicaStrategy(double factorMultiplicador) {
        this.factorMultiplicador = factorMultiplicador; // P. ej. 1.2 para un 20% más
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase * factorMultiplicador;
    }
}