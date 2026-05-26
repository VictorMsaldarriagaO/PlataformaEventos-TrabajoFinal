package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Apuntamos a la ubicación del paquete de tus vistas
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view_Controller/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Plataforma de Eventos - Autenticación");
        stage.setScene(scene);
        stage.show();
    }
}