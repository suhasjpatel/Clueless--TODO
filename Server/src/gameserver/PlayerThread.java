package gameserver;


import game.Game;
import players.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.UUID;

/**
 * Thread for client connections
 *
 * Created by Suhas on 10/16/2016.
 */
public class PlayerThread extends Thread {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Player player;
    private Game game;

    PlayerThread(Socket clientSocket, Game game) {
        this.socket = clientSocket;
        this.game = game;
    }

    public void run() {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            Game oldGame;
            UUID gameId = game.getGameId();

            //Send the game object to the client
            outputStream.writeObject(game);
            outputStream.flush();

            //Get the player details from the game
            try {
                game = (Game) inputStream.readObject();
                Server.setGame(game);
                player = game.getPlayers().get(game.getPlayers().size() - 1);
            } catch (SocketException e) {
                e.printStackTrace();
            }

            UUID playerId = player.getId();

            while (!Server.getGame(gameId).hasGameStarted()) {
                Thread.sleep(5000);
            }

            game = Server.getGame(gameId);
            assert game != null;
            for (Player p : game.getPlayers()) {
                if (p.getId().equals(playerId)) {
                    player = p;
                }
            }
            oldGame = Server.getGame(gameId);
            outputStream.writeObject(Server.getGame(gameId));
            outputStream.flush();

            while (!game.hasGameEnded()) {
                if (player.isTurn() || !Objects.equals(Server.getGame(gameId), oldGame)) {
                    if (player.isTurn()) {
                        game = (Game) inputStream.readObject();

                        for (Player p : game.getPlayers()) {
                            if (p.getId().equals(playerId)) {
                                if (p.isTurn()) {
                                    p.setTurn(false);
                                    p.getNextPlayer().setTurn(true);
                                    player = p;
                                    Server.setGame(game);
                                }
                            }
                        }
                    } else if (!Objects.equals(Server.getGame(gameId), oldGame)) {
                        outputStream.writeObject(Server.getGame(gameId));
                        outputStream.flush();
                        oldGame = Server.getGame(gameId);

                        for (Player p : Server.getGame(gameId).getPlayers()) {
                            if (p.getId().equals(playerId)) {
                                player = p;
                            }
                        }
                    }
                }
            }

            if (Server.getGame(gameId).hasGameEnded()) {
                System.out.println("game.Game" + gameId + "has ended");
                outputStream.writeObject(Server.getGame(gameId));
                outputStream.flush();
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
