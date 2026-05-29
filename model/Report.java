package model;

/**
 * Abstracción de documentos generados mediante el patrón Factory Method.
 */
public abstract class Report {
    public abstract void generate(Object data);
}