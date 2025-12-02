module com.example.oop2f25finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires com.example.oop2f25finalproject;


    opens com.example.oop2f25finalproject to javafx.fxml;
    exports com.example.oop2f25finalproject;
    exports com.example.oop2f25finalproject.Controllers;
    opens com.example.oop2f25finalproject.Controllers to javafx.fxml;
    exports com.example.oop2f25finalproject.Model;
    opens com.example.oop2f25finalproject.Model to javafx.fxml;
}