package gameserver;

import game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.System.exit;

/**
 * Server class from which the game will be run
 * <p>
 * Created by Suhas on 10/16/2016.
 */
public class Server {
    private static final int PORT = 7777;
    private static ArrayList<Game> games = new ArrayList<>();

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int NUM_PLAYERS = 6;

        try {
            if (!args[0].isEmpty()) {
                NUM_PLAYERS = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Server argument wasn't a number for the number of players");
            exit(-1);
        }

        try {
            serverSocket = new ServerSocket(PORT);
            UUID uuid = UUID.randomUUID();
            games.add(new Game(uuid));

            while (!serverSocket.isClosed()) {
                try {
                    System.out.println("Accepting connection");
                    socket = serverSocket.accept();
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }

                //Fill game slots with players
                if (getGame(uuid).getPlayers().size() != NUM_PLAYERS) {
                    System.out.println("Adding player to game: " + uuid);
                    new PlayerThread(socket, getGame(uuid)).start();
                    Thread.sleep(2000);
                }

                //Create new game if slots are full and new player joins
                else {
                    uuid = UUID.randomUUID();
                    System.out.println("creating game: " + uuid);
                    games.add(new Game(uuid));
                    new PlayerThread(socket, getGame(uuid)).start();
                    Thread.sleep(2000);
                }

                //If slots are full, start
                if (getGame(uuid).getPlayers().size() == NUM_PLAYERS) {
                    System.out.println("starting game: " + uuid);
                    getGame(uuid).start();
                }

                games.removeIf(Game::hasGameEnded);
            }

        } catch (IOException | InterruptedException e) {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    static Game getGame(UUID gameId) {
        for (Game g : games) {
            if (g.getGameId().equals(gameId)) {
                return g;
            }
        }
        return null;
    }

    static void setGame(Game game) {
        for (Game g : games) {
            if (g.getGameId().equals(game.getGameId())) {
                games.set(games.indexOf(g), game);
            }
        }
    }
}
