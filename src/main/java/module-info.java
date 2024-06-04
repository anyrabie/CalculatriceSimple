module com.example.rev {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.rev to javafx.fxml;
    exports com.example.rev;
}