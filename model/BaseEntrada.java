package model;

public class BaseEntrada implements EntradaComponent {
    private String idEntrada;
    private Zona zona;
    private Asiento asiento;

    public BaseEntrada(String idEntrada, Zona zona, Asiento asiento) {
        this.idEntrada = idEntrada;
        this.zona = zona;
        this.asiento = asiento;
    }

    @Override
    public double getPrecioFinal() { return zona.getPrecioCalculado(); }

    @Override
    public String getDetalles() {
        return "Entrada en " + zona.getNombre() + " (Asiento: " + asiento.getNumero() + ")";
    }

    @Override
    public Asiento getAsiento() {
        return this.asiento; // Implementación del nuevo método
    }
}