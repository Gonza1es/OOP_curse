package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.FloorRequest;
import com.example.oop_curse.model.Route;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    @FXML
    public Button floor1;

    @FXML
    public Button floor2;

    @FXML
    public Button floor3;

    @FXML
    public Button floor4;

    @FXML
    public Button floor5;

    @FXML
    public Button floor6;

    @FXML
    public Button floor7;

    @FXML
    public Button floor8;

    @FXML
    public Button floor9;

    @FXML
    public Button moveButton;

    @FXML
    public List<FloorRequest> initialize(Elevator elevator, Integer floorNumber) {
        List<FloorRequest> requestList = new ArrayList<>();
        floor1.setOnAction(actionEvent -> {
            if (floorNumber < Integer.parseInt(floor1.getText()))

            else route
        });
    }

}
