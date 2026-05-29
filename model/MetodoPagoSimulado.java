package model;

/**
 * DTO interno que representa el instrumento financiero provisto por el usuario.
 */
public class MetodoPagoSimulado {
    private String numeroTarjeta;

    public MetodoPagoSimulado(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroTarjeta() { return numeroTarjeta; }
}