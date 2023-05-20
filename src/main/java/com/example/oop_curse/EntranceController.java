package com.example.oop_curse;

import com.example.oop_curse.model.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class EntranceController {

    @FXML
    public TextField callingFloor;

    @FXML
    public ChoiceBox<Route> directionOfTravel;

    @FXML
    public Button callButton;

    @FXML
    public TextField passengersCount;

    @FXML
    private Slider elevator;

    @FXML
    protected void initialize() {
        ObservableList<Route> choiceBoxList = FXCollections.observableArrayList(Route.UP, Route.DOWN);
        directionOfTravel = new ChoiceBox<>(choiceBoxList);
    }
}