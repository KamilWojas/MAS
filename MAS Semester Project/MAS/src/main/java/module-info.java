module com.example.mas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mas to javafx.fxml;
    exports com.example.mas;
}