package model;

public interface EntradaComponent {
    double getPrecioFinal();
    String getDetalles();
    Asiento getAsiento(); // Nuevo método vital
}