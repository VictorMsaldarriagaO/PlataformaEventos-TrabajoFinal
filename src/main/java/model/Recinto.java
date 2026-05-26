package model;
import java.util.ArrayList;
import java.util.List;

public class Recinto {
    private String idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<Zona> zonas;

    public Recinto(String idRecinto, String nombre, String direccion, String ciudad) {
        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.zonas = new ArrayList<>();
    }

    public void agregarZona(Zona zona) {
        this.zonas.add(zona);
    }

    public String getIdRecinto() {
        return idRecinto;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public List<Zona> getZonas() {
        return zonas;
    }
}