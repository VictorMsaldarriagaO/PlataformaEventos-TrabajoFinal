package model;

public class CSVReport extends Report {
    @Override
    public void generate(Object data) {
        System.out.println("Generando archivo CSV con los datos de: " + data.toString());
        System.out.println("¡Reporte CSV exportado con éxito!");
    }
}