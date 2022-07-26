module com.example.carpre.carpre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens com.example.carpre.carpre to javafx.fxml;
    exports com.example.carpre.carpre;
}