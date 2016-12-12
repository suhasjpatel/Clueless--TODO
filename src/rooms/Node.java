package rooms;

import enums.Room;
import players.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class used to join together the game map via a linkedish list.
 * <p>
 * Created by Suhas on 10/16/2016.
 */
public abstract class Node implements Serializable {

    private Node left;
    private Node right;
    private Node up;
    private Node down;
    private Node diagonal;
    private ArrayList<Player> players = new ArrayList<>();
    private Room room;
    private String name;

    Node(String name) {
        this.name = name;
    }

    Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }

    Node getUp() {
        return up;
    }

    void setUp(Node up) {
        this.up = up;
    }

    Node getDown() {
        return down;
    }

    void setDown(Node down) {
        this.down = down;
    }

    Node getDiagonal() {
        return diagonal;
    }

    void setDiagonal(Node diagonal) {
        this.diagonal = diagonal;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public abstract boolean isRoom();

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

}
