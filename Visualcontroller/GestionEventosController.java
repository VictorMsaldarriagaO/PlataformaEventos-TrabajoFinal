package Visualcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class GestionEventosController {

    @FXML private TableView<Evento> tablaEventos;
    @FXML private TableColumn<Evento, String> colId;
    @FXML private TableColumn<Evento, String> colNombre;
    @FXML private TableColumn<Evento, String> colCategoria;
    @FXML private TableColumn<Evento, LocalDateTime> colFecha;
    @FXML private TableColumn<Evento, String> colRecinto;
    @FXML private TableColumn<Evento, EstadoEvento> colEstado;

    @FXML private TextField txtIdEvento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtDescripcion;
    @FXML private ComboBox<String> cbRecinto;
    @FXML private ComboBox<EstadoEvento> cbEstado;
    @FXML private Label lblMensaje;

    private ObservableList<Evento> listaEventos;
    private PlataformaService plataformaService = PlataformaService.getInstancia();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idEvento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));

        // Extracción del nombre del recinto a partir del objeto Evento
        colRecinto.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRecinto().getNombre())
        );
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoEvento"));

        cbRecinto.setItems(FXCollections.observableArrayList("Estadio Centenario", "Teatro Azul", "Coliseo del Café"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoEvento.values()));

        cargarDatosTabla();

        // Al dar clic en la tabla, se llena el formulario automáticamente
        tablaEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) { cargarEventoEnFormulario(newSelection); }
        });
    }

    private void cargarDatosTabla() {
        listaEventos = FXCollections.observableArrayList(plataformaService.getEventos());
        tablaEventos.setItems(listaEventos);
    }

    private void cargarEventoEnFormulario(Evento evento) {
        txtIdEvento.setText(evento.getIdEvento());
        txtNombre.setText(evento.getNombre());
        txtCategoria.setText(evento.getCategoria());
        txtDescripcion.setText(evento.getDescripcion());
        if (evento.getFechaHora() != null) { dpFecha.setValue(evento.getFechaHora().toLocalDate()); }
        cbRecinto.setValue(evento.getRecinto().getNombre());
        cbEstado.setValue(evento.getEstadoEvento());
    }

    @FXML
    void handleCrearEvento(ActionEvent event) {
        try {
            Recinto recintoSeleccionado = new Recinto("R-GEN", cbRecinto.getValue() != null ? cbRecinto.getValue() : "Desconocido", "Dir General", "Armenia");
            LocalDateTime fechaHora = dpFecha.getValue() != null ? dpFecha.getValue().atStartOfDay() : LocalDateTime.now().plusDays(15);

            Evento nuevoEvento = new Evento(txtIdEvento.getText(), txtNombre.getText(), txtCategoria.getText(), txtDescripcion.getText(),
                    "Armenia", fechaHora, "Política estándar", "Reembolso 100%", recintoSeleccionado);

            if (cbEstado.getValue() != null) { nuevoEvento.setEstadoEvento(cbEstado.getValue()); }

            plataformaService.agregarEvento(nuevoEvento);
            cargarDatosTabla();
            mostrarMensaje("Evento creado e insertado en memoria exitosamente.", "green");
        } catch (Exception e) {
            mostrarMensaje("Faltan campos por diligenciar.", "red");
        }
    }

    @FXML
    void handleActualizarEvento(ActionEvent event) {
        tablaEventos.refresh();
        mostrarMensaje("Datos en la tabla refrescados.", "blue");
    }

    @FXML
    void handleEliminarEvento(ActionEvent event) {
        Evento seleccionado = tablaEventos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            plataformaService.getEventos().remove(seleccionado);
            cargarDatosTabla();
            mostrarMensaje("Evento borrado de la cartelera.", "green");
        }
    }

    @FXML
    void handleCambiarEstado(ActionEvent event) {
        Evento seleccionado = tablaEventos.getSelectionModel().getSelectedItem();
        EstadoEvento nuevoEstado = cbEstado.getValue();
        if (seleccionado != null && nuevoEstado != null) {
            seleccionado.setEstadoEvento(nuevoEstado);
            tablaEventos.refresh();
            mostrarMensaje("El evento pasó a estado: " + nuevoEstado, "green");
        }
    }

    @FXML
    void handleVolver(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardAdministradorView.fxml"));
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void mostrarMensaje(String msj, String color) {
        lblMensaje.setText(msj);
        lblMensaje.setStyle("-fx-text-fill: " + color + ";");
    }
}