package at.fhtw.bif.swen.util;

import lombok.Getter;
import lombok.Setter;

public enum TransportType {
    AFOOT(1),
    CAR(2),
    TRAIN(3),
    PLANE(4),
    BOAT(5);

    @Getter
    @Setter
    private int value;

    TransportType(int value) {
        this.value = value;
    }

    public static TransportType parseValue(int value) {
        for (TransportType t : TransportType.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }
}
