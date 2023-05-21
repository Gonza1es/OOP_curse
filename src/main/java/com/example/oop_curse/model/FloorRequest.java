package com.example.oop_curse.model;

public record FloorRequest(Integer floorNumber, Integer passengersCount, Route route, Boolean isDelivery) implements Comparable<FloorRequest>{


    @Override
    public int compareTo(FloorRequest o) {
        return this.floorNumber - o.floorNumber;
    }
}
