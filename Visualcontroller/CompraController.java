package Visualcontroller;

import Controller.GestionCompraController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

/**
 * Administra la vista final de facturación, lee checkboxes dinámicos
 * para calcular precios en tiempo real y delega el cobro al controlador lógico.
 */
public class CompraController {

    @FXML
    private Label lblInfoEvento;

    @FXML
    private CheckBox chkVip;

    @FXML
    private CheckBox chkSeguro;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField txtTarjeta;

    @FXML
    private Button btnProcesar;

    @FXML
    private Label lblResultado;

    // NUEVOS COMBOBOX
    @FXML
    private ComboBox<String> cmbZonas;

    @FXML
    private ComboBox<String> cmbAsientos;

    @FXML
    private Button btnCancelar;

    private Evento eventoSeleccionado;
    private Zona zonaSeleccionada;
    private Asiento asientoSeleccionado;
    private GestionCompraController coordinadorLogico;

    @FXML
    public void initialize() {

        this.coordinadorLogico = new GestionCompraController();

        // Cargar zonas disponibles
        cmbZonas.getItems().addAll(
                "VIP",
                "General",
                "Preferencial"
        );
    }

    /**
     * Inicializa la información de compra.
     */
    public void inicializarDatosCompra(Evento evento) {

        this.eventoSeleccionado = evento;

        if (evento.getRecinto() != null &&
                !evento.getRecinto().getZonas().isEmpty()) {

            this.zonaSeleccionada =
                    evento.getRecinto().getZonas().get(0);

            if (!zonaSeleccionada.getAsientos().isEmpty()) {

                this.asientoSeleccionado =
                        zonaSeleccionada.getAsientos().get(0);
            }
        }

        lblInfoEvento.setText(
                "Espectáculo: " + evento.getNombre()
                        + " | Zona: "
                        + (zonaSeleccionada != null
                        ? zonaSeleccionada.getNombre()
                        : "N/A")
        );

        handleCambioAdicionales(null);
    }

    /**
     * Se ejecuta cuando el usuario cambia la zona.
     * Actualiza automáticamente los asientos disponibles.
     */
    @FXML
    private void handleCambioZona() {

        cmbAsientos.getItems().clear();

        String zona = cmbZonas.getValue();

        if (zona == null) {
            return;
        }

        switch (zona) {

            case "VIP":

                cmbAsientos.getItems().addAll(
                        "VIP-1",
                        "VIP-2",
                        "VIP-3"
                );

                break;

            case "General":

                cmbAsientos.getItems().addAll(
                        "G-1",
                        "G-2",
                        "G-3"
                );

                break;

            case "Preferencial":

                cmbAsientos.getItems().addAll(
                        "P-1",
                        "P-2",
                        "P-3"
                );

                break;
        }
    }

    /**
     * Recalcula el total cuando se activan extras.
     */
    @FXML
    void handleCambioAdicionales(ActionEvent event) {

        if (zonaSeleccionada == null) {
            return;
        }

        double total = zonaSeleccionada.getPrecioCalculado();

        if (chkVip.isSelected()) {
            total += 50000.0;
        }

        if (chkSeguro.isSelected()) {
            total += 15000.0;
        }

        lblTotal.setText("Total a Pagar: $" + total);
    }

    /**
     * Procesa la compra final.
     */
    @FXML
    void handleProcesarCompra(ActionEvent event) {

        String numTarjeta = txtTarjeta.getText().trim();

        if (numTarjeta.isEmpty()) {

            lblResultado.setText(
                    "¡Operación Denegada! Digite un número de tarjeta."
            );

            lblResultado.setStyle("-fx-text-fill: red;");

            return;
        }

        Usuario usuarioLogueado =
                PlataformaService.getInstancia()
                        .getUsuarioAutenticado();

        // DELEGACIÓN ESTRATÉGICA
        boolean exito =
                coordinadorLogico.ejecutarTransaccion(
                        usuarioLogueado,
                        eventoSeleccionado,
                        zonaSeleccionada,
                        asientoSeleccionado,
                        numTarjeta,
                        chkVip.isSelected(),
                        chkSeguro.isSelected()
                );

        if (exito) {

            lblResultado.setText(
                    "¡Compra Exitosa! Los logs del Observer se imprimieron en consola."
            );

            lblResultado.setStyle("-fx-text-fill: green;");

            btnProcesar.setDisable(true);

        } else {

            lblResultado.setText(
                    "Transacción Rechazada. Verifique fondos o datos."
            );

            lblResultado.setStyle("-fx-text-fill: red;");
        }
    }
    @FXML
    void handleCancelarCompra(ActionEvent event) {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();

        stage.close();
    }
}