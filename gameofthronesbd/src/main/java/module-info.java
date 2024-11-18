module org.example.gameofthronesbd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens org.example.gameofthronesbd to javafx.fxml;
    exports org.example.gameofthronesbd;
    exports org.example.gameofthronesbd.controller;
    opens org.example.gameofthronesbd.controller to javafx.fxml;
}