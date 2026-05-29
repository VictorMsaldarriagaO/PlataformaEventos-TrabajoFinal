package Visualcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Usuario;
import model.PlataformaService;

public class PerfilController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        Usuario user = PlataformaService
                .getInstancia()
                .getUsuarioAutenticado();

        txtNombre.setText(user.getNombreCompleto());
        txtCorreo.setText(user.getCorreoElectronico());
        txtTelefono.setText(user.getNumeroTelefono());
    }

    @FXML
    void handleGuardarCambios(ActionEvent event) {

        Usuario user = PlataformaService
                .getInstancia()
                .getUsuarioAutenticado();

        user.setNombreCompleto(txtNombre.getText());
        user.setCorreo(txtCorreo.getText());
        user.setTelefono(txtTelefono.getText());

        lblMensaje.setText("Cambios guardados correctamente");
        lblMensaje.setStyle("-fx-text-fill: green;");
    }
}