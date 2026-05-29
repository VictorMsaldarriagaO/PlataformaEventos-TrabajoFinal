package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Especialización de Persona que representa a un cliente dentro de la plataforma.
 * Contiene colecciones de métodos de pago válidos asociados.
 */
public class Usuario extends Persona {
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String numeroTelefono;
    private List<String> metodosPagoSimulados;

    public Usuario(String idUsuario, String nombreCompleto, String correoElectronico, String numeroTelefono) {
        super(idUsuario, nombreCompleto, correoElectronico, numeroTelefono);
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.metodosPagoSimulados = new ArrayList<>();
    }

    public void agregarMetodoPago(String metodo) {
        this.metodosPagoSimulados.add(metodo);
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getNumeroTelefono() { return numeroTelefono; }
    public List<String> getMetodosPagoSimulados() { return metodosPagoSimulados; }
}