package model;

public class PDFReport extends Report {
    @Override
    public void generate(Object data) {
        System.out.println("Generando archivo PDF con los datos de: " + data.toString());
        System.out.println("¡Reporte PDF exportado con éxito!");
    }
}