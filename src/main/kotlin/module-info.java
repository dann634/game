module com.jackson.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.jackson.main to javafx.fxml;
    exports com.jackson.main;
}