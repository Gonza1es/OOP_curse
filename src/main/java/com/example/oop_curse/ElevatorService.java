package com.example.oop_curse;

import com.example.oop_curse.model.FloorRequest;

import java.util.List;

public interface ElevatorService {

    Integer getCurrentFloor();

    Integer getCapacity();

    Integer getFilling();

    void setFilling(Integer filling);

    void setState();

    void callElevator(FloorRequest floorRequest);

    FloorRequest waitElevator(FloorRequest request);

    FloorRequest delivery();

    void addAllRequests(List<FloorRequest> floorRequests);

    Boolean isEmptyQueue();
}
