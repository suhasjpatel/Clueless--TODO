package com.todo.clueless.shared.models.players;

import com.todo.clueless.shared.models.enums.Card;
import com.todo.clueless.shared.models.enums.Room;
import com.todo.clueless.shared.models.enums.Suspect;
import com.todo.clueless.shared.models.enums.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
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

    public void setPossibleSuspects(ArrayList<Suspect> possibleSuspects) {
        this.possibleSuspects = possibleSuspects;
    }

    public ArrayList<Weapon> getPossibleWeapons() {
        return possibleWeapons;
    }

    public void setPossibleWeapons(ArrayList<Weapon> possibleWeapons) {
        this.possibleWeapons = possibleWeapons;
    }

    public ArrayList<Room> getPossibleRooms() {
        return possibleRooms;
    }

    public void setPossibleRooms(ArrayList<Room> possibleRooms) {
        this.possibleRooms = possibleRooms;
    }

    public ArrayList<Card> getSeenCards() {
        return seenCards;
    }

    public void setSeenCards(ArrayList<Card> seenCards) {
        this.seenCards = seenCards;
    }
}
