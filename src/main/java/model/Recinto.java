package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modela el escenario de infraestructura física donde se ejecutan los eventos.
 */
public class Recinto {
    private String idRecinto;
    private String nombre;
    private String direccion;
    private String city;
    private List<Zona> zonas;

    public Recinto(String idRecinto, String nombre, String direccion, String city) {
        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.city = city;
        this.zonas = new ArrayList<>();
    }

    public void agregarZona(Zona zona) {
        this.zonas.add(zona);
    }

    public String getIdRecinto() { return idRecinto; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getCity() { return city; }
    public List<Zona> getZonas() { return zonas; }
}