package Visualcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.IOException;

public class MetricasAdministradorController {

    @FXML private PieChart graficoOcupacion;
    @FXML private BarChart<String, Number> graficoVentas;

    @FXML
    public void initialize() {
        cargarDatosGraficos();
    }

    @FXML
    void handleActualizarGraficos(ActionEvent event) {
        cargarDatosGraficos();
    }

    private void cargarDatosGraficos() {
        // 1. Datos para PieChart (Distribución del Aforo)
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Zona VIP", 65),
                new PieChart.Data("Preferencial", 110),
                new PieChart.Data("General", 250)
        );
        graficoOcupacion.setData(pieData);

        // 2. Datos para BarChart (Desempeño de los Eventos)
        XYChart.Series<String, Number> seriesVentas = new XYChart.Series<>();
        seriesVentas.setName("Unidades Vendidas");
        seriesVentas.getData().add(new XYChart.Data<>("Concierto Filarmónico", 185));
        seriesVentas.getData().add(new XYChart.Data<>("Obra de Teatro", 90));
        seriesVentas.getData().add(new XYChart.Data<>("Festival Urbano", 320));

        graficoVentas.getData().clear();
        graficoVentas.getData().add(seriesVentas);
    }

    @FXML
    void handleVolver(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardAdministradorView.fxml"));
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) { e.printStackTrace(); }
    }
}