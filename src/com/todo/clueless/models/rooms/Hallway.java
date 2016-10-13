package com.todo.clueless.models.rooms;

import com.todo.clueless.models.enums.Room;

/**
 * Created by Suhas on 10/12/2016.
 */
public class Hallway{
    private Room source;
    private Room sink;

    public Hallway(Room source, Room sink){
        this.source = source;
        this.sink = sink;
    }
}
