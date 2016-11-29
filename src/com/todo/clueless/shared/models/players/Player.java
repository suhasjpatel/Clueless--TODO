package com.todo.clueless.shared.models.players;

import com.todo.clueless.shared.models.enums.Card;
import com.todo.clueless.shared.models.enums.Suspect;
import com.todo.clueless.shared.models.rooms.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Player implements Serializable {

    private String name;
    private Suspect character;
    private Actions lastAction = Actions.STARTING;
    private ArrayList<Card> cards = new ArrayList<>();
    private Notebook notebook = new Notebook();
    private UUID id = UUID.randomUUID();
    private boolean isHost = false;
    private boolean isTurn = false;
    private Player nextPlayer;

    private Node location;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Suspect getCharacter() {
        return character;
    }

    public void setCharacter(Suspect character) {
        this.character = character;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Actions getLastAction() {
        return lastAction;
    }

    public void setLastAction(Actions lastAction) {
        this.lastAction = lastAction;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public Node getLocation() {
        return location;
    }

    public void setLocation(Node location) {
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
}
