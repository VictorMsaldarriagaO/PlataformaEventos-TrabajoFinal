module com.example.plataformaeventos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.plataformaeventos to javafx.fxml;
    exports App;
    opens App to javafx.fxml;
}