package Visualcontroller;



import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.PlataformaService;
import model.Report;
import model.ReportGenerator;
import java.io.IOException;

/**
 * Controla la interfaz gráfica del Dashboard de Administrador.
 * Gestiona de forma centralizada la generación de métricas y la interacción de privilegios extendidos.
 */
public class DashboardAdministradorController {

    @FXML private Label lblBienvenida;
    @FXML private ComboBox<String> cbTipoReporte;
    @FXML private Button btnGenerar;
    @FXML private Label lblResultado;

    private ReportGenerator creadorReportes;

    /**
     * Inicializa los componentes de la interfaz de usuario y prepara el catálogo del Factory Method.
     */
    @FXML
    public void initialize() {
        this.creadorReportes = new ReportGenerator();

        // Cargar los tipos de reportes soportados en el ComboBox
        cbTipoReporte.setItems(FXCollections.observableArrayList("PDF", "CSV"));

        // Mensaje estático o personalizado para el rol administrativo
        lblBienvenida.setText("Panel de Control - Administrador");
    }

    /**
     * Captura el evento de generación de archivos analíticos, instanciando los objetos
     * correspondientes de manera dinámica sin acoplamientos rígidos.
     */
    @FXML
    void handleGenerarReporte(ActionEvent event) {
        String tipoSeleccionado = cbTipoReporte.getValue();

        if (tipoSeleccionado == null || tipoSeleccionado.isEmpty()) {
            lblResultado.setText("Operación Cancelada: Por favor seleccione un formato de reporte válido.");
            lblResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Delegación de la instanciación al Factory Method concreto
            Report reporteAnalitico = creadorReportes.createReport(tipoSeleccionado);

            // Recopilación simulada de la base de conocimientos persistente en memoria
            int totalEventos = PlataformaService.getInstancia().getEventos().size();
            String datosConsolidados = "Métricas Globales de la Plataforma [Eventos Totales: " + totalEventos + "]";

            // Ejecución del algoritmo especializado de codificación y salida en consola
            reporteAnalitico.generate(datosConsolidados);

            lblResultado.setText("¡Éxito! El reporte estructurado " + tipoSeleccionado + " fue enviado a la consola del sistema.");
            lblResultado.setStyle("-fx-text-fill: green;");

        } catch (IllegalArgumentException e) {
            lblResultado.setText("Error Operacional: " + e.getMessage());
            lblResultado.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Redirecciona el flujo del ciclo de vida visual de la ventana hacia la pantalla de autenticación.
     */
    @FXML
    void handleCerrarSesion(ActionEvent event) {
        try {
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Plataforma de Eventos - Autenticación");
            stage.setScene(scene);
        } catch (IOException e) {
            lblResultado.setText("Error crítico al intentar cargar la vista de Login.");
            lblResultado.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
    @FXML
    void handleIrGestionEventos(ActionEvent event) {
        try {
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestionEventosView.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Gestión y CRUD de Eventos");
        } catch (IOException e) {
            lblResultado.setText("Error al abrir Gestión de Eventos.");
            e.printStackTrace();
        }
    }

    @FXML
    void handleIrMetricas(ActionEvent event) {
        try {
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MetricasAdministradorView.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Análisis de Métricas");
        } catch (IOException e) {
            lblResultado.setText("Error al abrir Métricas.");
            e.printStackTrace();
        }
    }
}