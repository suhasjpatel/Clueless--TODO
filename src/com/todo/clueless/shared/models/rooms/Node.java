package com.todo.clueless.shared.models.rooms;

import com.todo.clueless.shared.models.enums.Room;
import com.todo.clueless.shared.models.players.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
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

    public Node(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Node getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Node diagonal) {
        this.diagonal = diagonal;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public abstract boolean isRoom();

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
