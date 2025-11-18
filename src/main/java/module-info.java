module com.example.oop2f25finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop2f25finalproject to javafx.fxml;
    exports com.example.oop2f25finalproject;
}