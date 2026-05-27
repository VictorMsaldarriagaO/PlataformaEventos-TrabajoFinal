package Visualcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Evento;
import model.PlataformaService;
import model.Usuario;
import java.io.IOException;

/**
 * Controla visualmente el listado de cartelera de eventos disponibles para compra.
 */
public class DashboardController {

    @FXML private Label lblBienvenida;
    @FXML private ListView<String> lvEventos;
    @FXML private Button btnSiguiente;
    @FXML private Label lblError;

    private ObservableList<Evento> listaEventosModelo;

    @FXML
    public void initialize() {
        Usuario autenticado = PlataformaService.getInstancia().getUsuarioAutenticado();
        if (autenticado != null) {
            lblBienvenida.setText("Bienvenido, " + autenticado.getNombreCompleto());
        }

        listaEventosModelo = FXCollections.observableArrayList(PlataformaService.getInstancia().getEventos());
        ObservableList<String> nombresVisuales = FXCollections.observableArrayList();
        for (Evento ev : listaEventosModelo) {
            nombresVisuales.add(ev.getNombre() + " - " + ev.getCiudad() + " (" + ev.getCategoria() + ")");
        }
        lvEventos.setItems(nombresVisuales);
    }

    @FXML
    void handleSeleccionarEvento(ActionEvent event) {
        int filaSeleccionada = lvEventos.getSelectionModel().getSelectedIndex();
        if (filaSeleccionada < 0) {
            lblError.setText("Debe seleccionar un evento de la lista para continuar.");
            return;
        }

        Evento eventoSeleccionado = listaEventosModelo.get(filaSeleccionada);

        try {
            Stage stage = (Stage) lvEventos.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CompraView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            CompraController compraCtrl = fxmlLoader.getController();
            compraCtrl.inicializarDatosCompra(eventoSeleccionado);

            stage.setTitle("Procesar Reserva y Pago");
            stage.setScene(scene);
        } catch (IOException e) {
            lblError.setText("Error al abrir el módulo de checkout.");
            e.printStackTrace();
        }
    }
}