package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    public Button startButton;

    @FXML
    public void initialize() {
        startButton.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("entrance.fxml"));
                Scene scene = new Scene(fxmlLoader1.load(), 394, 426);
                EntranceController entranceController = fxmlLoader1.getController();
                Elevator elevator = new Elevator(4, 0, State.STAY, 1);
                entranceController.initialize(elevator);
                Stage stage = new Stage();
                stage.setTitle("Подъезд 1");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("entrance.fxml"));
                Scene scene = new Scene(fxmlLoader2.load(), 394, 426);
                EntranceController entranceController = fxmlLoader2.getController();
                Elevator elevator = new Elevator(4, 0, State.STAY, 1);
                entranceController.initialize(elevator);
                Stage stage = new Stage();
                stage.setTitle("Подъезд 2");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
