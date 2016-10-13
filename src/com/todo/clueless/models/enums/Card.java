package com.todo.clueless.models.enums;

public enum Card {
    KNIFE, CANDLESTICK, REVOLVER, ROPE, LEAD_PIPE, WRENCH, SCARLET, MUSTARD, WHITE, GREEN, PEACOCK, PLUM, KITCHEN, BALLROOM, CONSERVATORY, DINING_ROOM, BILLIARD_ROOM, LIBRARY, LOUNGE, HALL, STUDY;

    public boolean isRoom() {
        return this == BALLROOM || this == BILLIARD_ROOM
                || this == CONSERVATORY || this == DINING_ROOM || this == HALL
                || this == KITCHEN || this == LIBRARY || this == LOUNGE
                || this == STUDY;
    }

    public boolean isWeapon() {
        return this == CANDLESTICK || this == KNIFE || this == LEAD_PIPE
                || this == REVOLVER || this == ROPE || this == WRENCH;
    }

    public boolean isSuspect() {
        return this == GREEN || this == SCARLET || this == MUSTARD
                || this == PEACOCK || this == PLUM || this == WHITE;
    }
}
