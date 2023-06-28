package com.svajdlenka.travelpostcard.tools.boundaries;

public enum PropertyKey {
    NAME("name"),
    WIDTH("width"),
    HEIGHT("height"),
    X_MIN("xmin"),
    X_MAX("xmax"),
    Y_MIN("ymin"),
    Y_MAX("ymax"),

    ;

    private String key;

    private PropertyKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
