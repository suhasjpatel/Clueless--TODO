package players;

import enums.Card;
import enums.Room;
import enums.Suspect;
import enums.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a player's notebook.
 *
 * Created by Suhas on 11/6/2016.
 */
public class Notebook implements Serializable {
    private ArrayList<Room> possibleRooms = new ArrayList<>();
    private ArrayList<Suspect> possibleSuspects = new ArrayList<>();
    private ArrayList<Weapon> possibleWeapons = new ArrayList<>();

    private ArrayList<Card> seenCards = new ArrayList<>();

    public Notebook() {
        Collections.addAll(possibleSuspects, Suspect.values());
        Collections.addAll(possibleWeapons, Weapon.values());
        Collections.addAll(possibleRooms, Room.values());
    }

    public ArrayList<Suspect> getPossibleSuspects() {
        return possibleSuspects;
    }

    public ArrayList<Weapon> getPossibleWeapons() {
        return possibleWeapons;
    }

    public ArrayList<Room> getPossibleRooms() {
        return possibleRooms;
    }

    public ArrayList<Card> getSeenCards() {
        return seenCards;
    }
}
