package com.example.oop_curse;

import com.example.oop_curse.model.Elevator;
import com.example.oop_curse.model.FloorRequest;
import com.example.oop_curse.model.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void setFilling(Integer filling) {
        elevator.setFilling(filling);
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
            elevator.setFilling(currentFloor.passengersCount());
        }
        return currentFloor;
    }

    @Override
    public FloorRequest delivery() {
        return elevator.moveToNextFloor();
    }

    @Override
    public void addAllRequests(List<FloorRequest> floorRequests) {
        elevator.addAllRequests(sortRequests(floorRequests));
    }

    @Override
    public Boolean isEmptyQueue() {
        return elevator.getRouteQueue().isEmpty();
    }

    private List<FloorRequest> sortRequests(List<FloorRequest> requestList) {
        List<FloorRequest> result = new ArrayList<>();
        List<FloorRequest> resultDown = new ArrayList<>();
        List<FloorRequest> resultUp = new ArrayList<>();
        Map<Route, List<FloorRequest>> map = requestList.stream()
                .collect(Collectors.groupingBy(FloorRequest::route));
        for (Map.Entry<Route, List<FloorRequest>> item : map.entrySet()) {
            if (item.getKey().equals(Route.DOWN)) {
                item.getValue().sort(((o1, o2) -> o2.floorNumber() - o1.floorNumber()));
                resultDown.addAll(item.getValue());
            } else {
                item.getValue().sort(((o1, o2) -> o1.floorNumber() - o2.floorNumber()));
                resultUp.addAll(item.getValue());
            }
        }
        if (resultDown.size() > resultUp.size()) {
            result.addAll(resultUp);
            result.addAll(resultDown);
        } else {
            result.addAll(resultDown);
            result.addAll(resultUp);
        }

        return result;
    }
}
