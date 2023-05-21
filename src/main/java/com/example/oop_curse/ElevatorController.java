package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.FloorRequest;
import com.example.oop_curse.model.Route;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
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
    public TextField passengersCountForFloor2;
    public TextField passengersCountForFloor3;
    public TextField passengersCountForFloor4;
    public TextField passengersCountForFloor5;
    public TextField passengersCountForFloor1;
    public TextField passengersCountForFloor7;
    public TextField passengersCountForFloor8;
    public TextField passengersCountForFloor9;
    public TextField passengersCountForFloor6;

    private ElevatorService elevatorService;

    private FloorRequest currentFloor;

    @FXML
    public void initialize(ElevatorService elevatorService, FloorRequest currentFloor) {
        this.elevatorService = elevatorService;
        this.currentFloor = currentFloor;
        List<FloorRequest> requestList = new ArrayList<>();
        floor1.setOnAction(actionEvent -> buttonClick(floor1, passengersCountForFloor1, requestList));
        floor2.setOnAction(actionEvent -> buttonClick(floor2, passengersCountForFloor2, requestList));
        floor3.setOnAction(actionEvent -> buttonClick(floor3, passengersCountForFloor3, requestList));
        floor4.setOnAction(actionEvent -> buttonClick(floor4, passengersCountForFloor4, requestList));
        floor5.setOnAction(actionEvent -> buttonClick(floor5, passengersCountForFloor5, requestList));
        floor6.setOnAction(actionEvent -> buttonClick(floor6, passengersCountForFloor6, requestList));
        floor7.setOnAction(actionEvent -> buttonClick(floor7, passengersCountForFloor7, requestList));
        floor8.setOnAction(actionEvent -> buttonClick(floor8, passengersCountForFloor8, requestList));
        floor9.setOnAction(actionEvent -> buttonClick(floor9, passengersCountForFloor9, requestList));

        moveButton.setOnAction(actionEvent -> {
            Collections.sort(requestList);
            elevatorService.addAllRequests(requestList);
            Stage stage = (Stage) moveButton.getScene().getWindow();
            stage.close();
        });
    }

    private boolean validatePayload(TextField passengersCountForFloor) {
        Integer passengersCount = Integer.parseInt(passengersCountForFloor.getText());
        return passengersCountForFloor.getText().isBlank()
                || passengersCount >= elevatorService.getCapacity() - elevatorService.getFilling()
                || passengersCount > elevatorService.getCapacity()
                || passengersCount > currentFloor.floorNumber();

    }

    private FloorRequest createFloorRequest(Integer floorNumber, Integer currentFloor, Integer passengerCount, Route route) {
        if (floorNumber.equals(currentFloor)) {
            showAlert("Вы уже находитесь на этом этаже");
            return null;
        }
        return new FloorRequest(floorNumber, passengerCount, route);
    }

    private void buttonClick(Button floor, TextField passengersCount, List<FloorRequest> requestList) {
        if (validatePayload(passengersCount)) {
            showAlert(ErrorDescription.INVALID_PASSENGERS_COUNT);
        } else {
            Integer passengerCountForFloor = Integer.parseInt(passengersCount.getText());
            Integer floorNumber = Integer.parseInt(floor.getText());
            Route route = elevatorService.getCurrentFloor() > floorNumber
                    ? Route.UP
                    : Route.DOWN;
            FloorRequest floorRequest = createFloorRequest(floorNumber,
                    elevatorService.getCurrentFloor(),
                    passengerCountForFloor,
                    route);
            if (floorRequest != null) {
                requestList.add(floorRequest);
            }
        }
    }

    private void showAlert(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorText);
        alert.showAndWait();
    }

}
