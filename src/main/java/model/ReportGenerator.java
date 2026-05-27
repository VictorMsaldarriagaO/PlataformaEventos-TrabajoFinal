package model;

/**
 * Creador concreto del Factory Method que encapsula la toma de decisiones de instanciación.
 */
public class ReportGenerator {
    public Report createReport(String type) {
        if (type == null || type.isEmpty()) return null;
        switch (type.toUpperCase()) {
            case "PDF": return new PDFReport();
            case "CSV": return new CSVReport();
            default: throw new IllegalArgumentException("Tipo no soportado: " + type);
        }
    }
}