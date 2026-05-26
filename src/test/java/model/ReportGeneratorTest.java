package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorTest {

    @Test
    public void testGeneracionReportePDF() {
        Report reporte = ReportGenerator.crearReporte("PDF");
        assertTrue(reporte instanceof PDFReport);
    }

    @Test
    public void testGeneracionReporteCSV() {
        Report reporte = ReportGenerator.crearReporte("CSV");
        assertTrue(reporte instanceof CSVReport);
    }
}