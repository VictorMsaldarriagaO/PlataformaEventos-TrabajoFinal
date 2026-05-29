package Visualcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Evento;
import model.PlataformaService;
import model.Usuario;
import javafx.scene.Parent;



import java.io.IOException;

/**
 * Controla la visualización de la cartelera de eventos, el filtrado y la navegación.
 */
public class DashboardController {

    @FXML private Label lblBienvenida;
    @FXML private ListView<String> lvEventos;
    @FXML private ComboBox<String> cmbCategorias;
    @FXML private DatePicker dateFiltro;
    @FXML private Button btnIrHistorial;
    @FXML private Label lblError;

    private ObservableList<Evento> listaEventosModelo;

    @FXML
    public void initialize() {
        // 1. Mostrar saludo al usuario autenticado
        Usuario autenticado = PlataformaService.getInstancia().getUsuarioAutenticado();
        if (autenticado != null) {
            lblBienvenida.setText("Bienvenido, " + autenticado.getNombreCompleto());
        }

        // 2. Cargar eventos iniciales y llenar el ListView
        listaEventosModelo = FXCollections.observableArrayList(PlataformaService.getInstancia().getEventos());
        actualizarListaEventos(listaEventosModelo);

        // 3. Inicializar opciones del ComboBox de categorías
        cmbCategorias.setItems(FXCollections.observableArrayList("Concierto", "Teatro", "Festival", "Deportivo"));
    }

    /**
     * Llena el ListView con los nombres visuales de los eventos.
     */
    private void actualizarListaEventos(ObservableList<Evento> eventos) {
        ObservableList<String> nombresVisuales = FXCollections.observableArrayList();
        for (Evento ev : eventos) {
            nombresVisuales.add(ev.getNombre() + " - " + ev.getCiudad() + " (" + ev.getCategoria() + ")");
        }
        lvEventos.setItems(nombresVisuales);
    }

    @FXML
    void handleFiltrar(ActionEvent event) {
        String categoria = cmbCategorias.getValue();
        // Implementación básica de filtrado
        lblError.setText("Filtrando por: " + (categoria != null ? categoria : "Todos"));
        // Aquí podrías añadir lógica adicional para filtrar por fecha (dateFiltro.getValue())
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

            // Pasamos el evento seleccionado al controlador de compra
            CompraController compraCtrl = fxmlLoader.getController();
            compraCtrl.inicializarDatosCompra(eventoSeleccionado);

            stage.setTitle("Procesar Reserva y Pago");
            stage.setScene(scene);
        } catch (IOException e) {
            lblError.setText("Error al cargar la pantalla de compra.");
            e.printStackTrace();
        }
    }

    @FXML
    void handleIrHistorial(ActionEvent event) {
        try {
            Stage stage = (Stage) btnIrHistorial.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HistorialView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Historial de Compras");
            stage.setScene(scene);
        } catch (IOException e) {
            lblError.setText("Error al cargar el historial.");
            e.printStackTrace();
        }
    }
    @FXML
    void handleAbrirPerfil(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PerfilView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Mi Perfil");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}