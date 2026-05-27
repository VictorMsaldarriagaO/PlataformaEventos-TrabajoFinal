package model;

/**
 * Especialización de Persona con privilegios extendidos para la toma de decisiones,
 * como la generación y parametrización de reportes analíticos de la plataforma.
 */
public class Administrador extends Persona {
    public Administrador(String id, String nombre, String correo, String tel) {
        super(id, nombre, correo, tel);
    }
}