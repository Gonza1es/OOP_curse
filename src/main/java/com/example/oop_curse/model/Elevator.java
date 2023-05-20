package com.example.oop_curse.model;

import java.util.*;

public class Elevator {

    private final Integer CAPACITY;

    private Integer filling;

    private State state;

    private final Queue<FloorRequest> routeQueue = new PriorityQueue<>();

    public Elevator(Integer CAPACITY, Integer filling, State state) {
        this.CAPACITY = CAPACITY;
        this.filling = filling;
        this.state = state;
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

    public void addAllRequests(List<FloorRequest> floorRequests) {
        routeQueue.addAll(floorRequests);
    }
}
