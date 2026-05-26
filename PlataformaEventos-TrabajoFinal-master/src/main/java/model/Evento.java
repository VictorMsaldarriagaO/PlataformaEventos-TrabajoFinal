package model;
import java.time.LocalDateTime;

public class Evento {
    private String idEvento;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String ciudad;
    private LocalDateTime fechaHora;
    private EstadoEvento estadoEvento; // Usando el enum específico
    private String politicaCancelacion;
    private String politicaReembolso;
    private Recinto recinto;

    public Evento(String idEvento, String nombre, String categoria, String descripcion,
                  String ciudad, LocalDateTime fechaHora, String politicaCancelacion,
                  String politicaReembolso, Recinto recinto) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.fechaHora = fechaHora;
        this.politicaCancelacion = politicaCancelacion;
        this.politicaReembolso = politicaReembolso;
        this.estadoEvento = EstadoEvento.BORRADOR; // Estado inicial por defecto
        this.recinto = recinto;
    }

    public String getIdEvento() {
        return idEvento;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }
    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public String getPoliticaCancelacion() {
        return politicaCancelacion;
    }
    public String getPoliticaReembolso() {
        return politicaReembolso;
    }
    public Recinto getRecinto() {
        return recinto;
    }
}
