package com.todo.clueless.client;

import com.todo.clueless.server.Game;
import com.todo.clueless.shared.models.enums.Card;
import com.todo.clueless.shared.models.enums.Room;
import com.todo.clueless.shared.models.enums.Suspect;
import com.todo.clueless.shared.models.enums.Weapon;
import com.todo.clueless.shared.models.players.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Suhas on 10/12/2016.
 */
public class Client extends Application {
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    private static Player player;
    private static Game game;

    public static void main(String args[]) {
        launch(args);
    }

    /**
     * Initializes a player session with the server
     *
     * @param player The player object created at the welcome page
     */
    public static void createPlayerAndSession(Player player) {
        try {
            Socket socket = new Socket("127.0.0.1", 22222);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            //Get the game object from the server, and add this player to it, then send it back
            game = (Game) inputStream.readObject();
            game.addPlayer(player);
            outputStream.writeObject(game);
            outputStream.flush();

            //Get an updated game object once all players have connected.
            game = (Game) inputStream.readObject();

            //Get an updated version of this client's player based on the ID.
            for (Player p : game.getPlayers()) {
                if (p.getId().equals(player.getId())) {
                    Client.player = p;
                }
            }

            //Keep an inputStream open to look for new game objects being sent to the client from the server thread
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                while (true) {
                                    game = (Game) inputStream.readObject();

                                    for (Player p : game.getPlayers()) {
                                        if (p.getId().equals(player.getId())) {
                                            Client.player = p;

                                                /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/gameView.fxml"));
                                                BorderPane p1 = fxmlLoader.load();
                                                GameController gameController = fxmlLoader.getController();
                                                gameController.updateOptions();
                                                fxmlLoader.setController(gameController);
                                                */
                                        }
                                    }
                                }
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 0, 10000000);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkSuggestion(String suspectString, String weaponString, String roomString) {
        Suspect suspect = Card.toCard(suspectString).toSuspect();
        Weapon weapon = Card.toCard(weaponString).toWeapon();
        Room room = Card.toCard(roomString).toRoom();

        game.getPlayers().stream().filter(p -> p.getCharacter().equals(suspect)).forEach(p -> {
            p.setLocation(game.getMap().roomNodeforRoom(room));
        });
        //Comments maybe needed for bluffs?
        for (Player p : game.getPlayers()) {
            if (p.getCards().contains(suspect.toCard())) {
                //Client.player.getNotebook().getPossibleSuspects().remove(suspect);
                Client.player.getNotebook().getSeenCards().add(suspect.toCard());
                return;
            } else if (p.getCards().contains(weapon.toCard())) {
                //Client.player.getNotebook().getPossibleWeapons().remove(weapon);
                Client.player.getNotebook().getSeenCards().add(weapon.toCard());
                return;

            } else if (p.getCards().contains(room.toCard())) {
                //Client.player.getNotebook().getPossibleRooms().remove(room);
                Client.player.getNotebook().getSeenCards().add(room.toCard());
                return;
            }
        }

        System.out.println("No one had any of these cards, you win if you accuse with them");
    }

    public static void checkAccusation(String suspectString, String weaponString, String roomString) {

        Suspect suspect = Card.toCard(suspectString).toSuspect();
        Weapon weapon = Card.toCard(weaponString).toWeapon();
        Room room = Card.toCard(roomString).toRoom();

        if (game.getCardDeck().getCaseFile().getRoomCard().equals(room.toCard()) && game.getCardDeck().getCaseFile().getSuspectCard().equals(suspect.toCard()) && game.getCardDeck().getCaseFile().getWeaponCard().equals(weapon.toCard())) {
            System.out.println("You win");
        }
    }

    public static void endTurn() {
        try {
            outputStream.writeObject(game);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Client.player = player;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Client.game = game;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/welcomeView.fxml"));
        Scene scene;
        scene = new Scene(root, 300, 275);
        stage.setTitle("Clueless");
        stage.setScene(scene);
        stage.show();
    }
}
