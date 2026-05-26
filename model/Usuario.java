package model;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String numeroTelefono;
    private List<String> metodosPagoSimulados; // Lo mantenemos simple hasta implementar el Adapter

    public Usuario(String idUsuario, String nombreCompleto, String correoElectronico, String numeroTelefono) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.metodosPagoSimulados = new ArrayList<>();
    }

    public void agregarMetodoPago(String metodo) {
        this.metodosPagoSimulados.add(metodo);
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    public List<String> getMetodosPagoSimulados() {
        return metodosPagoSimulados;
    }
}