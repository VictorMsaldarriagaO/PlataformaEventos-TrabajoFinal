package model;

/**
 * Interfaz unificada del patrón Decorator. Define el comportamiento del tiquete.
 */
public interface EntradaComponent {
    double getPrecioFinal();
    String getDetalles();
    Asiento getAsiento();
}