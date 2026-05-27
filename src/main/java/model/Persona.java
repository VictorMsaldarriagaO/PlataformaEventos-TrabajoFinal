package model;

/**
 * Clase abstracta que define la estructura biográfica básica de cualquier individuo.
 * Aplica el principio de Responsabilidad Única (SRP) y Sustitución de Liskov (LSP).
 */
public abstract class Persona {
    private String id;
    private String nombreCompleto;
    private String correo;
    private String telefono;

    public Persona(String id, String nombreCompleto, String correo, String telefono) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombre) { this.nombreCompleto = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}