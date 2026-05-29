package Visualcontroller;

import Controller.AutenticacionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private Label lblError;
    @FXML private Button btnIrRegistro;

    private AutenticacionController logicaController;

    @FXML
    public void initialize() {
        this.logicaController = new AutenticacionController();
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (correo.isEmpty() || telefono.isEmpty()) {
            lblError.setText("Por favor, complete todos los campos.");
            return;
        }

        boolean exito = logicaController.procesarAutenticacion(correo, telefono);

        if (exito) {
            try {
                Stage stage = (Stage) txtCorreo.getScene().getWindow();
                FXMLLoader fxmlLoader;
                Scene scene;

                // OBTENEMOS EL USUARIO AUTENTICADO
                model.Usuario usuarioLogueado = model.PlataformaService.getInstancia().getUsuarioAutenticado();

                // LÓGICA DE ROLES: Aquí definimos quién es administrador.
                // Puedes cambiar "admin@eventos.com" por el correo que uses para tu admin.
                if (correo.equalsIgnoreCase("admin@eventos.com") || correo.toLowerCase().contains("admin")) {

                    // 1. CARGAMOS LA VISTA DEL ADMINISTRADOR
                    fxmlLoader = new FXMLLoader(getClass().getResource("/DashboardAdministradorView.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Panel de Control - Administrador");

                } else {

                    // 2. CARGAMOS LA VISTA DEL CLIENTE NORMAL
                    fxmlLoader = new FXMLLoader(getClass().getResource("/DashboardView.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Cartelera de Eventos");

                }

                stage.setScene(scene);

            } catch (IOException e) {
                lblError.setText("Error crítico al cargar el entorno.");
                e.printStackTrace();
            }
        } else {
            lblError.setText("Credenciales incorrectas o usuario no encontrado.");
        }
    }

    @FXML
    void handleIrRegistro(ActionEvent event) {
        try {
            // Obtenemos el stage actual
            Stage stage = (Stage) btnIrRegistro.getScene().getWindow();

            // Cargamos la vista de registro (asegúrate de que el archivo exista en recursos)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RegistroView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Registro de Usuario");
            stage.setScene(scene);
        } catch (IOException e) {
            lblError.setText("Error al cargar la pantalla de registro.");
            e.printStackTrace();
        }
    }
}