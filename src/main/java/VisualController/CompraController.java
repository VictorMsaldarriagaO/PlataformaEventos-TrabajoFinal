package VisualController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.*;

public class CompraController {

    @FXML private Label lblInfoEvento;
    @FXML private CheckBox chkVip;
    @FXML private CheckBox chkSeguro;
    @FXML private Label lblTotal;
    @FXML private TextField txtTarjeta;
    @FXML private Button btnProcesar;
    @FXML private Label lblResultado;

    private Evento eventoSeleccionado;
    private Zona zonaSeleccionada;
    private Asiento asientoSeleccionado;
    private CompraFacade fachadaCompras;

    @FXML
    public void initialize() {
        this.fachadaCompras = new CompraFacade();
    }

    public void inicializarDatosCompra(Evento evento) {
        this.eventoSeleccionado = evento;

        // Para propósitos del flujo estructurado, tomamos la primera zona y asiento disponibles del recinto
        if (evento.getRecinto() != null && !evento.getRecinto().getZonas().isEmpty()) {
            this.zonaSeleccionada = evento.getRecinto().getZonas().get(0);
            if (!zonaSeleccionada.getAsientos().isEmpty()) {
                this.asientoSeleccionado = zonaSeleccionada.getAsientos().get(0);
            }
        }

        if (zonaSeleccionada != null && asientoSeleccionado != null) {
            lblInfoEvento.setText("Evento: " + evento.getNombre() + " | Localidad: " + zonaSeleccionada.getNombre() +
                    " | Asiento: " + asientoSeleccionado.getFila() + asientoSeleccionado.getNumero());
        }

        actualizarCalculoVisualPrecio();
    }

    @FXML
    void handleCambioAdicionales(ActionEvent event) {
        actualizarCalculoVisualPrecio();
    }

    private void actualizarCalculoVisualPrecio() {
        if (zonaSeleccionada == null) return;

        // Consume el algoritmo dinámico de precios (Patrón Strategy de la zona)
        double total = zonaSeleccionada.getPrecioCalculado();

        // Emulación visual rápida del impacto del Decorator en la UI
        if (chkVip.isSelected()) total += 50000.0;
        if (chkSeguro.isSelected()) total += 15000.0;

        lblTotal.setText("Total a Pagar: $" + total);
    }

    @FXML
    void handleProcesarCompra(ActionEvent event) {
        String numTarjeta = txtTarjeta.getText().trim();
        if (numTarjeta.isEmpty()) {
            lblResultado.setText("¡Operación Denegada! Digite un número de tarjeta.");
            lblResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        Usuario usuarioLogueado = PlataformaService.getInstancia().getUsuarioAutenticado();

        // DELEGACIÓN ESTRATÉGICA: La Fachada procesa el Builder, Decorator, Adapter, State y Observer por detrás
        boolean exito = fachadaCompras.efectuarCompra(
                usuarioLogueado,
                eventoSeleccionado,
                zonaSeleccionada,
                asientoSeleccionado,
                numTarjeta,
                chkVip.isSelected(),
                chkSeguro.isSelected()
        );

        if (exito) {
            lblResultado.setText("¡Compra Exitosa! Los logs del Observer se imprimieron en consola.");
            lblResultado.setStyle("-fx-text-fill: green;");
            btnProcesar.setDisable(true); // Evita duplicidad en el procesamiento de la transacción
        } else {
            lblResultado.setText("Transacción Rechazada. Verifique fondos simulados.");
            lblResultado.setStyle("-fx-text-fill: red;");
        }
    }
}