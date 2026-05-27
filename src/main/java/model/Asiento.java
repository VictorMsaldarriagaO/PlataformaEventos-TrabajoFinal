package model;

/**
 * Representa la unidad mínima indivisible de aforo dentro de un espacio físico.
 */
public class Asiento {
    private String idAsiento;
    private String fila;
    private int numero;
    private EstadoAsiento estadoAsiento;

    public Asiento(String idAsiento, String fila, int numero) {
        this.idAsiento = idAsiento;
        this.fila = fila;
        this.numero = numero;
        this.estadoAsiento = EstadoAsiento.DISPONIBLE;
    }

    public String getIdAsiento() { return idAsiento; }
    public void setIdAsiento(String idAsiento) { this.idAsiento = idAsiento; }

    public String getFila() { return fila; }
    public void setFila(String fila) { this.fila = fila; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public EstadoAsiento getEstadoAsiento() { return estadoAsiento; }
    public void setEstadoAsiento(EstadoAsiento estadoAsiento) { this.estadoAsiento = estadoAsiento; }
}