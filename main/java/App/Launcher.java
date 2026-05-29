package App;

import javafx.application.Application;

/**
 * Punto de entrada plano estándar para la Máquina Virtual de Java (JVM).
 * Evita problemas de validación de dependencias de tiempo de ejecución en entornos modulares
 * al no extender directamente de Application.
 */
public class Launcher {
    public static void main(String[] args) {
        // Redirecciona el flujo al ciclo de vida controlado de JavaFX
        Application.launch(StartApplication.class, args);
    }
}