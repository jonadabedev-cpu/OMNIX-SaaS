package com.omnix.enums;

public enum EventType {
    WEDDING("Casamento"),
    BIRTHDAY("Aniversário"),
    CORPORATE("Coporativo"),
    GRADUATION("Formatura"),
    PARTY("Festa"),
    OTHER("Outros");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
