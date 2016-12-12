package rooms;

import enums.Room;

/**
 * Node to represent a room object on the GameMap
 * <p>
 * Created by Suhas on 10/30/2016.
 */
public class RoomNode extends Node {

    RoomNode(Room room, String name) {
        super(name);
        setRoom(room);
    }

    @Override
    public boolean isRoom() {
        return true;
    }
}
