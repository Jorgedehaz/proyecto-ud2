module org.example.gameofthronesbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens org.example.gameofthronesbd to javafx.fxml;
    opens org.example.gameofthronesbd.controller to javafx.fxml;
    exports org.example.gameofthronesbd;
    exports org.example.gameofthronesbd.controller;
}