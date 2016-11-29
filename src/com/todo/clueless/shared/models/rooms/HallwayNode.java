package com.todo.clueless.shared.models.rooms;

/**
 * Created by Suhas on 10/30/2016.
 */
public class HallwayNode extends Node {

    public HallwayNode(String name) {
        super(name);
    }

    @Override
    public boolean isRoom() {
        return false;
    }
}
