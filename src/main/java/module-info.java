module com.example.portfolio2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.portfolio2 to javafx.fxml;
    exports com.example.portfolio2;
}