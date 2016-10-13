package com.todo.clueless.models.enums;

public enum Suspect {
    MUSTARD, SCARLET, PLUM, GREEN, WHITE, PEACOCK;
    public Card toCard() {
        switch (this) {
            case MUSTARD:
                return Card.MUSTARD;
            case SCARLET:
                return Card.SCARLET;
            case PLUM:
                return Card.PLUM;
            case GREEN:
                return Card.GREEN;
            case WHITE:
                return Card.WHITE;
            case PEACOCK:
                return Card.PEACOCK;
            default:
                return null;
        }
    }
}
