package com.example.oop_curse.model;

public enum Route {

    UP("Вверх"),
    DOWN("Вниз");

    private String label;

    Route(String value) {
        this.label = value;
    }

    public String getLabel() {
        return this.label;
    }

    public static Route valueOfLabel(String label) {
        for (Route route: values()) {
            if (route.label.equals(label))
                return route;
        }
        return null;
    }
}
