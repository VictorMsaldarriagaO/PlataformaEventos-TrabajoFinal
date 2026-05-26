package VisualController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PlataformaService;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private Label lblError;

    @FXML
    void handleLogin(ActionEvent event) {
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (correo.isEmpty() || telefono.isEmpty()) {
            lblError.setText("Por favor, complete todos los campos.");
            return;
        }

        // Validación consumiendo el patrón Singleton del Modelo
        boolean exito = PlataformaService.getInstancia().iniciarSesion(correo, telefono);

        if (exito) {
            try {
                // Transición fluida de escena al Dashboard de eventos
                Stage stage = (Stage) txtCorreo.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view_Controller/DashboardView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Cartelera de Eventos");
                stage.setScene(scene);
            } catch (IOException e) {
                lblError.setText("Error al cargar la vista del Dashboard.");
                e.printStackTrace();
            }
        } else {
            lblError.setText("Usuario no registrado. Pruebe con: josedavid@email.com");
        }
    }
}