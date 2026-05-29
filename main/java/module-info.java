/**
 * Descriptor de módulo del sistema. Configura el sistema modular de Java (JPMS).
 * Define las dependencias requeridas y abre las capas de la aplicación mediante
 * reflexión para que JavaFX pueda procesar los archivos FXML y los bindings de datos.
 * * @author Víctor Manuel Saldarriaga, Jose David Jarava, Juan Andrés Hernandez
 */
module com.example.plataformaeventos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Permite que la infraestructura de JavaFX instancie y enlace las vistas con los controladores visuales
    opens Visualcontroller to javafx.fxml;

    // Abre el modelo para permitir enlace automático de propiedades (Bindings) en componentes como TableView o ListView
    opens model to javafx.base;

    // Exporta las clases base de arranque de la aplicación
    exports App;
    opens App to javafx.fxml;
}