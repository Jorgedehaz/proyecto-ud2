module org.example.gameofthronesbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens org.example.gameofthronesbd.model to javafx.base;
    opens org.example.gameofthronesbd to javafx.fxml;
    opens org.example.gameofthronesbd.controller to javafx.fxml;

    exports org.example.gameofthronesbd;
    exports org.example.gameofthronesbd.controller;
    exports org.example.gameofthronesbd.model;
}