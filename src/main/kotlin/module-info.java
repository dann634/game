module com.jackson.kotlinplatformer {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.jackson to javafx.fxml;
    exports com.jackson;
}