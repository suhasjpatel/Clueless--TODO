package players;

import enums.Card;
import enums.Suspect;
import rooms.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Player implements Serializable {

    private String name;
    private Suspect character;
    private ArrayList<Card> cards = new ArrayList<>();
    private Notebook notebook = new Notebook();
    private UUID id = UUID.randomUUID();
    private boolean isTurn = false;
    private Player nextPlayer;
    private Node location;
    private boolean hasMoved;
    private boolean hasSuggested;
    private boolean hasFalselyAccused = false;
    private boolean endTurnUpdate = false;
    private boolean firstUpdate = true;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public Notebook getNotebook() {
        return notebook;
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

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasSuggested() {
        return hasSuggested;
    }

    public void setHasSuggested(boolean hasSuggested) {
        this.hasSuggested = hasSuggested;
    }

    public boolean hasFalselyAccused() {
        return hasFalselyAccused;
    }

    public void setHasFalselyAccused(boolean hasFalselyAccused) {
        this.hasFalselyAccused = hasFalselyAccused;
    }

    public boolean isEndTurnUpdate() {
        return endTurnUpdate;
    }

    public void setEndTurnUpdate(boolean endTurnUpdate) {
        this.endTurnUpdate = endTurnUpdate;
    }

    public boolean isFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(boolean firstUpdate) {
        this.firstUpdate = firstUpdate;
    }
}
