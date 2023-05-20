module com.example.oop_curse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_curse to javafx.fxml;
    exports com.example.oop_curse.model;
    exports com.example.oop_curse;
}