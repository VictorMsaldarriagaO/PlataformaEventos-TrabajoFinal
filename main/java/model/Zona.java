package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Segmento del recinto que delimita un aforo específico
 * y un precio base estándar.
 */
public class Zona {

    private String idZona;
    private String nombre;
    private int capacidad;
    private double precioBase;
    private List<Asiento> asientos;

    // STRATEGY (Corregido el nombre de la interfaz)
    private PricingStrategy estrategiaTarifa;

    public Zona(String idZona,
                String nombre,
                int capacidad,
                double precioBase) {

        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.asientos = new ArrayList<>();

        // Estrategia por defecto
        this.estrategiaTarifa = new TarifaEstandarStrategy();
    }

    /**
     * Calcula el precio usando el patrón Strategy.
     */
    public double getPrecioCalculado() {
        return estrategiaTarifa.calcularPrecio(this.precioBase);
    }

    /**
     * Permite cambiar la estrategia dinámicamente.
     */
    public void setEstrategiaTarifa(PricingStrategy estrategiaTarifa) {
        this.estrategiaTarifa = estrategiaTarifa;
    }

    public void agregarAsiento(Asiento asiento) {
        if (asientos.size() < capacidad) {
            this.asientos.add(asiento);
        }
    }

    public String getIdZona() { return idZona; }
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
    public List<Asiento> getAsientos() { return asientos; }
}