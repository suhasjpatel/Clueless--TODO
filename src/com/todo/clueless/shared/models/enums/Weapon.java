package com.todo.clueless.shared.models.enums;

public enum Weapon {
    ROPE, LEAD_PIPE, KNIFE, WRENCH, CANDLESTICK, REVOLVER;

    public Card toCard() {
        switch (this) {
            case ROPE:
                return Card.ROPE;
            case LEAD_PIPE:
                return Card.LEAD_PIPE;
            case KNIFE:
                return Card.KNIFE;
            case WRENCH:
                return Card.WRENCH;
            case CANDLESTICK:
                return Card.CANDLESTICK;
            case REVOLVER:
                return Card.REVOLVER;
            default:
                return null;
        }
    }
}
