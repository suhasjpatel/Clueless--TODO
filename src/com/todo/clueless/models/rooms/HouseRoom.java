package com.todo.clueless.models.rooms;

import com.todo.clueless.models.enums.Room;
import com.todo.clueless.models.players.Player;

import java.util.List;

public class HouseRoom {
    private Room room;
    private List<Player> roomPlayers;

    public HouseRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void Room(Room room) {
        this.room = room;
    }
}
