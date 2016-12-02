package enums;

public enum Card {
    KNIFE, CANDLESTICK, REVOLVER, ROPE, LEAD_PIPE, WRENCH, SCARLET, MUSTARD, WHITE, GREEN, PEACOCK, PLUM, KITCHEN, BALLROOM, CONSERVATORY, DINING_ROOM, BILLIARD_ROOM, LIBRARY, LOUNGE, HALL, STUDY;

    public static Card toCard(String s) {
        switch (s) {
            case "Knife":
                return KNIFE;
            case "Candlestick":
                return CANDLESTICK;
            case "Revolver":
                return REVOLVER;
            case "Rope":
                return ROPE;
            case "Lead Pipe":
                return LEAD_PIPE;
            case "Wrench":
                return WRENCH;
            case "Miss Scarlet":
                return SCARLET;
            case "Colonel Mustard":
                return MUSTARD;
            case "Mrs. White":
                return WHITE;
            case "Mr. Green":
                return GREEN;
            case "Mrs. Peacock":
                return PEACOCK;
            case "Professor Plum":
                return PLUM;
            case "Kitchen":
                return KITCHEN;
            case "Ballroom":
                return BALLROOM;
            case "Conservatory":
                return CONSERVATORY;
            case "Dining Room":
                return DINING_ROOM;
            case "Billiard Room":
                return BILLIARD_ROOM;
            case "Library":
                return LIBRARY;
            case "Lounge":
                return LOUNGE;
            case "Hall":
                return HALL;
            case "Study":
                return STUDY;
            default:
                return null;
        }
    }

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

    public Room toRoom() {
        switch (this) {
            case BALLROOM:
                return Room.BALLROOM;
            case BILLIARD_ROOM:
                return Room.BILLIARD_ROOM;
            case CONSERVATORY:
                return Room.CONSERVATORY;
            case DINING_ROOM:
                return Room.DINING_ROOM;
            case HALL:
                return Room.HALL;
            case KITCHEN:
                return Room.KITCHEN;
            case LIBRARY:
                return Room.LIBRARY;
            case LOUNGE:
                return Room.LOUNGE;
            case STUDY:
                return Room.STUDY;
            default:
                return null;
        }
    }

    public Suspect toSuspect() {
        switch (this) {
            case MUSTARD:
                return Suspect.MUSTARD;
            case SCARLET:
                return Suspect.SCARLET;
            case PLUM:
                return Suspect.PLUM;
            case GREEN:
                return Suspect.GREEN;
            case WHITE:
                return Suspect.WHITE;
            case PEACOCK:
                return Suspect.PEACOCK;
            default:
                return null;
        }
    }

    public Weapon toWeapon() {
        switch (this) {
            case ROPE:
                return Weapon.ROPE;
            case LEAD_PIPE:
                return Weapon.LEAD_PIPE;
            case KNIFE:
                return Weapon.KNIFE;
            case WRENCH:
                return Weapon.WRENCH;
            case CANDLESTICK:
                return Weapon.CANDLESTICK;
            case REVOLVER:
                return Weapon.REVOLVER;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case KNIFE:
                return "Knife";
            case CANDLESTICK:
                return "Candlestick";
            case REVOLVER:
                return "Revolver";
            case ROPE:
                return "Rope";
            case LEAD_PIPE:
                return "Lead Pipe";
            case WRENCH:
                return "Wrench";
            case SCARLET:
                return "Miss Scarlet";
            case MUSTARD:
                return "Colonel Mustard";
            case WHITE:
                return "Mrs. White";
            case GREEN:
                return "Mr. Green";
            case PEACOCK:
                return "Mrs. Peacock";
            case PLUM:
                return "Professor Plum";
            case KITCHEN:
                return "Kitchen";
            case BALLROOM:
                return "Ballroom";
            case CONSERVATORY:
                return "Conservatory";
            case DINING_ROOM:
                return "Dining Room";
            case BILLIARD_ROOM:
                return "Billiard Room";
            case LIBRARY:
                return "Library";
            case LOUNGE:
                return "Lounge";
            case HALL:
                return "Hall";
            case STUDY:
                return "Study";
            default:
                return "";
        }
    }
}
