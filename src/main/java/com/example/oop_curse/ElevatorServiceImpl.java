package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.FloorRequest;

import java.util.List;

public class ElevatorServiceImpl implements ElevatorService {

    private final Elevator elevator;

    public ElevatorServiceImpl(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public Integer getCurrentFloor() {
        return elevator.getCurrentFloor();
    }

    @Override
    public Integer getCapacity() {
        return elevator.getCAPACITY();
    }

    @Override
    public Integer getFilling() {
        return elevator.getFilling();
    }

    @Override
    public void setState() {
        elevator.setState();
    }

    @Override
    public void callElevator(FloorRequest floorRequest) {
        elevator.addRequest(floorRequest);
    }

    @Override
    public FloorRequest waitElevator(FloorRequest request) {
        FloorRequest currentFloor = null;
        while (!elevator.getCurrentFloor().equals(request.floorNumber())) {
            currentFloor = elevator.moveToNextFloor();
        }
        return currentFloor;
    }

    @Override
    public FloorRequest delivery() {
        return elevator.moveToNextFloor();
    }

    @Override
    public void addAllRequests(List<FloorRequest> floorRequests) {
        elevator.addAllRequests(floorRequests);
    }

    @Override
    public Boolean isEmptyQueue() {
        return elevator.getRouteQueue().isEmpty();
    }


}
