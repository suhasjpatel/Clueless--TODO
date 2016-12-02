package rooms;

import enums.Room;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a game map object where all the rooms are properly arranged.
 *
 * Created by Suhas on 10/16/2016.
 */
public class GameMap implements Serializable {
    public RoomNode kitchen = new RoomNode(Room.KITCHEN, Room.KITCHEN.toCard().toString());
    private RoomNode ballroom = new RoomNode(Room.BALLROOM, Room.BALLROOM.toCard().toString());
    private RoomNode conservatory = new RoomNode(Room.CONSERVATORY, Room.CONSERVATORY.toCard().toString());
    private RoomNode library = new RoomNode(Room.LIBRARY, Room.LIBRARY.toCard().toString());
    private RoomNode billiardRoom = new RoomNode(Room.BILLIARD_ROOM, Room.BILLIARD_ROOM.toCard().toString());
    private RoomNode diningRoom = new RoomNode(Room.DINING_ROOM, Room.DINING_ROOM.toCard().toString());
    private RoomNode lounge = new RoomNode(Room.LOUNGE, Room.LOUNGE.toCard().toString());
    private RoomNode hall = new RoomNode(Room.HALL, Room.HALL.toCard().toString());
    private RoomNode study = new RoomNode(Room.STUDY, Room.STUDY.toCard().toString());

    private HallwayNode hallway1 = new HallwayNode("Hallway 1");
    private HallwayNode hallway2 = new HallwayNode("Hallway 2");
    private HallwayNode hallway3 = new HallwayNode("Hallway 3");
    private HallwayNode hallway4 = new HallwayNode("Hallway 4");
    private HallwayNode hallway5 = new HallwayNode("Hallway 5");
    private HallwayNode hallway6 = new HallwayNode("Hallway 6");
    private HallwayNode hallway7 = new HallwayNode("Hallway 7");
    private HallwayNode hallway8 = new HallwayNode("Hallway 8");
    private HallwayNode hallway9 = new HallwayNode("Hallway 9");
    private HallwayNode hallway10 = new HallwayNode("Hallway 10");
    private HallwayNode hallway11 = new HallwayNode("Hallway 11");
    private HallwayNode hallway12 = new HallwayNode("Hallway 12");


    public GameMap() {
        kitchen.setLeft(hallway1);
        kitchen.setUp(hallway8);
        kitchen.setDiagonal(study);

        ballroom.setLeft(hallway2);
        ballroom.setRight(hallway1);
        ballroom.setUp(hallway9);

        conservatory.setRight(hallway2);
        conservatory.setUp(hallway3);
        conservatory.setDiagonal(lounge);

        library.setRight(hallway10);
        library.setUp(hallway4);
        library.setDown(hallway3);

        billiardRoom.setLeft(hallway10);
        billiardRoom.setRight(hallway12);
        billiardRoom.setUp(hallway11);
        billiardRoom.setDown(hallway9);

        diningRoom.setLeft(hallway12);
        diningRoom.setUp(hallway7);
        diningRoom.setDown(hallway8);

        lounge.setLeft(hallway6);
        lounge.setDown(hallway7);
        lounge.setDiagonal(conservatory);

        hall.setLeft(hallway5);
        hall.setRight(hallway6);
        hall.setUp(hallway11);

        study.setRight(hallway5);
        study.setDown(hallway4);
        study.setDiagonal(kitchen);

        hallway1.setLeft(ballroom);
        hallway1.setRight(kitchen);

        hallway2.setLeft(conservatory);
        hallway2.setRight(ballroom);

        hallway3.setUp(library);
        hallway3.setDown(conservatory);

        hallway4.setUp(study);
        hallway4.setDown(library);

        hallway5.setLeft(study);
        hallway5.setRight(hall);

        hallway6.setLeft(hall);
        hallway6.setRight(lounge);

        hallway7.setUp(lounge);
        hallway7.setDown(diningRoom);

        hallway8.setUp(diningRoom);
        hallway8.setDown(kitchen);

        hallway9.setUp(billiardRoom);
        hallway9.setDown(ballroom);

        hallway10.setLeft(library);
        hallway10.setRight(billiardRoom);

        hallway11.setUp(hall);
        hallway11.setDown(billiardRoom);

        hallway12.setLeft(billiardRoom);
        hallway12.setRight(diningRoom);
    }

    public HallwayNode getHallway1() {
        return hallway1;
    }

    public HallwayNode getHallway2() {
        return hallway2;
    }

    public HallwayNode getHallway3() {
        return hallway3;
    }

    public HallwayNode getHallway4() {
        return hallway4;
    }

    public HallwayNode getHallway6() {
        return hallway6;
    }

    public HallwayNode getHallway7() {
        return hallway7;
    }

    public RoomNode roomNodeforRoom(Room room) {
        switch (room) {
            case KITCHEN:
                return kitchen;
            case BALLROOM:
                return ballroom;
            case CONSERVATORY:
                return conservatory;
            case LIBRARY:
                return library;
            case BILLIARD_ROOM:
                return billiardRoom;
            case DINING_ROOM:
                return diningRoom;
            case LOUNGE:
                return lounge;
            case HALL:
                return hall;
            case STUDY:
                return study;
            default:
                return null;
        }
    }

    public ArrayList<String> availableMovesFromNode(Node node) {
        ArrayList<String> availableMoves = new ArrayList<>();

        //If its a room, all available rooms are hallways or diagonals
        if (node.isRoom()) {
            if (node.getLeft() != null && node.getLeft().getPlayers().size() == 0) {
                availableMoves.add(node.getLeft().getName());
            }
            if (node.getRight() != null && node.getRight().getPlayers().size() == 0) {
                availableMoves.add(node.getRight().getName());
            }
            if (node.getDiagonal() != null) {
                availableMoves.add(node.getDiagonal().getName());
            }
            if (node.getUp() != null && node.getUp().getPlayers().size() == 0) {
                availableMoves.add(node.getUp().getName());
            }
            if (node.getDown() != null && node.getDown().getPlayers().size() == 0) {
                availableMoves.add(node.getDown().getName());
            }
        }
        //If its not a room, all available rooms are actual rooms
        else {
            if (node.getLeft() != null) {
                availableMoves.add(node.getLeft().getName());
            }
            if (node.getRight() != null) {
                availableMoves.add(node.getRight().getName());
            }
            if (node.getUp() != null) {
                availableMoves.add(node.getUp().getName());
            }
            if (node.getDown() != null) {
                availableMoves.add(node.getDown().getName());
            }
        }
        return availableMoves;
    }

    public Node nodeFromString(String s) {
        switch (s) {
            case "Hallway 1":
                return hallway1;
            case "Hallway 2":
                return hallway2;
            case "Hallway 3":
                return hallway3;
            case "Hallway 4":
                return hallway4;
            case "Hallway 5":
                return hallway5;
            case "Hallway 6":
                return hallway6;
            case "Hallway 7":
                return hallway7;
            case "Hallway 8":
                return hallway8;
            case "Hallway 9":
                return hallway9;
            case "Hallway 10":
                return hallway10;
            case "Hallway 11":
                return hallway11;
            case "Hallway 12":
                return hallway12;
            case "Kitchen":
                return kitchen;
            case "Ballroom":
                return ballroom;
            case "Conservatory":
                return conservatory;
            case "Dining Room":
                return diningRoom;
            case "Billiard Room":
                return billiardRoom;
            case "Library":
                return library;
            case "Lounge":
                return lounge;
            case "Hall":
                return hall;
            case "Study":
                return study;
            default:
                return null;
        }
    }
}
