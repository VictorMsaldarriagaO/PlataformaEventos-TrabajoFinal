package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Inicializador principal del contenedor de interfaz gráfica de JavaFX.
 * Carga la escena inicial de Autenticación y parametriza la ventana principal.
 */
public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Usamos el recurso del Classpath absoluto anteponiendo la barra diagonal '/'
        java.net.URL fxmlLocation = getClass().getResource("/LoginView.fxml");

        if (fxmlLocation == null) {
            throw new IOException("No se pudo encontrar LoginView.fxml. Revisa que esté dentro de resources/Visualcontroller/");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Plataforma de Eventos - Autenticación");
        stage.setScene(scene);
        stage.show();
    }
}