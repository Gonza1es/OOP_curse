package com.example.oop_curse.model;

public enum Route {

    UP("Вверх"),
    DOWN("Вниз");

    private String value;

    Route(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
