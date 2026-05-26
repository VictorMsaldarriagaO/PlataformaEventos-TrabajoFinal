package model;
// TarifaEstandarStrategy.java (Implementación 1: Precio normal)
public class TarifaEstandarStrategy implements PricingStrategy {
    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase; // No hace modificaciones
    }
}