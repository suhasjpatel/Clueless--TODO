package rooms;

/**
 * Node to represent a hallway object on the GameMap
 * <p>
 * Created by Suhas on 10/30/2016.
 */
public class HallwayNode extends Node {

    HallwayNode(String name) {
        super(name);
    }

    @Override
    public boolean isRoom() {
        return false;
    }
}
