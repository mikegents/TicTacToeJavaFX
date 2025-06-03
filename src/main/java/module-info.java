module com.example.tictactoejfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoejfx to javafx.fxml;
    exports com.example.tictactoejfx;
}