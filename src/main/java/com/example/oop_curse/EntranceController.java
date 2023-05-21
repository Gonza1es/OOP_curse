package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.FloorRequest;
import com.example.oop_curse.model.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class EntranceController {

    @FXML
    public TextField callingFloor;

    @FXML
    public ChoiceBox<String> directionOfTravel;

    @FXML
    public Button callButton;

    @FXML
    public TextField passengersCount;

    @FXML
    private Slider elevatorImage;

    private ElevatorService elevatorService;

    boolean hasNext = true;

    @FXML
    protected void initialize(Elevator elevator) {
        elevatorService = new ElevatorServiceImpl(elevator);
        ObservableList<String> choiceBoxList = FXCollections.observableArrayList(Route.UP.getLabel(), Route.DOWN.getLabel());
        directionOfTravel.setItems(choiceBoxList);
        AtomicReference<FloorRequest> currentFloor = new AtomicReference<>();
        callButton.setOnAction(actionEvent -> {
            String floorNumber = callingFloor.getText();
            String passengers = passengersCount.getText();
            Route route = Route.valueOfLabel(directionOfTravel.getValue());
            if (!showAlert(floorNumber, passengers, route)) {
                FloorRequest floorRequest = new FloorRequest(Integer.parseInt(floorNumber), Integer.parseInt(passengers), route);
                elevatorService.callElevator(floorRequest);
                currentFloor.set(elevatorService.waitElevator(floorRequest));
                if (currentFloor.get() != null) {
                    elevatorImage.adjustValue(currentFloor.get().floorNumber());
                    elevatorService.setState();
                }
            }
        });
        elevatorImage.valueProperty().addListener((observableValue, number, t1) -> {
            if (currentFloor.get().floorNumber().equals(elevatorService.getCurrentFloor())) {
                try {
                    showElevatorScene(currentFloor.get());
                    delivery();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void delivery() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (hasNext)
                    updateUI();
                return null;
            }
        };
        elevatorImage.valueChangingProperty().bind(task.runningProperty());
        new Thread(task).start();
    }

    private void updateUI() {
        FloorRequest request = elevatorService.delivery();
        if (request != null)
            this.elevatorImage.setValue(request.floorNumber());
        else hasNext = false;
    }

    private void showElevatorScene(FloorRequest currentFloor) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Elevator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 225, 309);
        Stage stage = new Stage();
        ElevatorController elevatorController = fxmlLoader.getController();
        elevatorController.initialize(elevatorService, currentFloor);
        stage.setTitle("Лифт");
        stage.setScene(scene);
        stage.showAndWait();
    }


    private boolean showAlert(String floorNumber, String passengers, Route route) {
        if (floorNumber.isBlank() || passengers.isBlank() || route == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Необходимо заполнить все поля для вызова лифта");
            alert.showAndWait();
            return true;
        }
        return false;
    }
}