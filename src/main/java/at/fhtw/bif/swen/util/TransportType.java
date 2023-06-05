package at.fhtw.bif.swen.util;

import lombok.Getter;

import java.util.ArrayList;

public enum TransportType {
    EMPTY(0, ""),
    AFOOT(1, "Foot"),
    CAR(2, "Car"),
    TRAIN(3, "Train"),
    PLANE(4, "Plane"),
    BOAT(5, "Boat");


    @Getter
    private final int value;

    @Getter
    private final String name;

    TransportType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TransportType parseValue(int value) {
        for (TransportType t : TransportType.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }

    public static TransportType parseName(String name) {
        for (TransportType t : TransportType.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
