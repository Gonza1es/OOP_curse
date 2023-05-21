package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.State;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("entrance.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 394, 426);
        EntranceController entranceController = fxmlLoader.getController();
        Elevator elevator = new Elevator(4, 0, State.STAY, 1);
        entranceController.initialize(elevator);
        stage.setTitle("Подъезд 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}