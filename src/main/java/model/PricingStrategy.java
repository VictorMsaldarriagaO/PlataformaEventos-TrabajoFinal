package model;

/**
 * Interfaz base para el patrón Strategy. Permite modificar algoritmos de precios dinámicamente.
 */
public interface PricingStrategy {
    double calcularPrecio(double precioBase);
}