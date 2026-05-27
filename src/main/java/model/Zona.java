package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Segmento del recinto que delimita un aforo específico y un precio base estándar.
 */
public class Zona {
    private String idZona;
    private String nombre;
    private int capacidad;
    private double precioBase;
    private List<Asiento> asientos;

    public Zona(String idZona, String nombre, int capacidad, double precioBase) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.asientos = new ArrayList<>();
    }

    public double getPrecioCalculado() {
        return this.precioBase;
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