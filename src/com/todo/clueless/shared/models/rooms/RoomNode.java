package com.todo.clueless.shared.models.rooms;

import com.todo.clueless.shared.models.enums.Room;

/**
 * Created by Suhas on 10/30/2016.
 */
public class RoomNode extends Node {

    public RoomNode(Room room, String name) {
        super(name);
        setRoom(room);
    }

    @Override
    public boolean isRoom() {
        return true;
    }
}
