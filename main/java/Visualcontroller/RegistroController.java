package Visualcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PlataformaService;
import model.Usuario;
import java.io.IOException;
import java.util.UUID;

/**
 * Gestiona la lógica de interfaz para la creación de nuevos usuarios.
 */
public class RegistroController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private Label lblMensaje;

    @FXML
    void handleRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String tel = txtTelefono.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty() || tel.isEmpty()) {
            lblMensaje.setText("¡Por favor completa todos los campos!");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }

        // Crear instancia y registrar usando Singleton
        Usuario nuevoUsuario = new Usuario(UUID.randomUUID().toString(), nombre, correo, tel);
        PlataformaService.getInstancia().registrarUsuario(nuevoUsuario);

        lblMensaje.setText("¡Usuario registrado con éxito!");
        lblMensaje.setStyle("-fx-text-fill: green;");

        // Limpiar campos tras éxito
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
    }

    @FXML
    void handleVolver(ActionEvent event) {
        try {
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Plataforma de Eventos - Autenticación");
            stage.setScene(scene);
        } catch (IOException e) {
            lblMensaje.setText("Error al volver al login.");
            e.printStackTrace();
        }
    }
}