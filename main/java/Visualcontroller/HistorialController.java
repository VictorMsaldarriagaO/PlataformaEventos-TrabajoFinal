package Visualcontroller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.*; // Importa todo el modelo
import java.io.IOException;
import java.util.List;

public class HistorialController {

    @FXML private TableView<Compra> tblCompras;
    @FXML private TableColumn<Compra, String> colId;
    @FXML private TableColumn<Compra, String> colEvento;
    @FXML private TableColumn<Compra, String> colFecha;
    @FXML private TableColumn<Compra, String> colTotal;
    @FXML private TableColumn<Compra, String> colEstado;

    @FXML
    public void initialize() {
        // Configuración de celdas - Aquí ya no debería marcar error
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdCompra()));
        colEvento.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEvento().getNombre()));
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaCompra().toString()));
        colTotal.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().calcularTotalCompra())));
        colEstado.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEstadoActual().getClass().getSimpleName()));

        cargarCompras();
    }

    private void cargarCompras() {
        Usuario usuario = PlataformaService.getInstancia().getUsuarioAutenticado();
        if (usuario != null) {
            List<Compra> lista = PlataformaService.getInstancia().getComprasDelUsuario(usuario.getIdUsuario());
            tblCompras.setItems(FXCollections.observableArrayList(lista));
        }
    }

    @FXML
    void handleVolver(ActionEvent event) {
        try {
            Stage stage = (Stage) tblCompras.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DashboardView.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Cartelera de Eventos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}