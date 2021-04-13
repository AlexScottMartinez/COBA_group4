package com.example.coba_group4.occurence;

import java.util.ArrayList;

public enum OccurrenceEventType {
    MINOR,
    LOW,
    MEDIUM,
    HIGH,
    SEVERE;

    public static ArrayList<String> names() {
        OccurrenceEventType[] eventTypes = values();
        ArrayList<String> names = new ArrayList<>();

        for (int index = 0; index < eventTypes.length; index++) {
            names.add(eventTypes[index].name());
        }
        return names;
    }
}
