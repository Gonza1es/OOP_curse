package com.example.oop_curse.model;

import java.util.*;

public class Elevator {

    private final Integer CAPACITY;

    private Integer filling;

    private State state;

    private Integer currentFloor;

    private final Queue<FloorRequest> routeQueue = new LinkedList<>();

    public Elevator(Integer CAPACITY, Integer filling, State state, Integer currentFloor) {
        this.CAPACITY = CAPACITY;
        this.filling = filling;
        this.state = state;
        this.currentFloor = currentFloor;
    }

    public void setState() {
        switch (state) {
            case MOVE -> state = State.STAY;
            case STAY -> state = State.MOVE;
        }
    }

    public void addRequest(FloorRequest request) {
        routeQueue.add(request);
    }

    public FloorRequest moveToNextFloor() {
        setState();
        if (!routeQueue.isEmpty()) {
            FloorRequest request = routeQueue.peek();
            int time = Math.abs(request.floorNumber() - currentFloor);
            try {
                Thread.sleep(time * 1000L);
                this.currentFloor = request.floorNumber();
                setState();
                if (filling != 0)
                    filling -= request.passengersCount();
                routeQueue.poll();
                return request;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public void addAllRequests(List<FloorRequest> floorRequests) {
        routeQueue.addAll(floorRequests);
    }

    public Integer getCAPACITY() {
        return CAPACITY;
    }

    public Integer getFilling() {
        return filling;
    }

    public void setFilling(Integer filling) {
        this.filling = filling;
    }

    public State getState() {
        return state;
    }

    public Queue<FloorRequest> getRouteQueue() {
        return routeQueue;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }
}
