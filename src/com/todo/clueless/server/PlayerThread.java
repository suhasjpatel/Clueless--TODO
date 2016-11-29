package com.todo.clueless.server;

import com.todo.clueless.shared.models.players.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Suhas on 10/16/2016.
 */
public class PlayerThread extends Thread {
    protected Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Player player;
    private Game game;

    PlayerThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            Game oldGame;

            //Send the game object to the client
            outputStream.writeObject(Server.getGame());
            outputStream.reset();

            //Get the player details from the game
            try {
                game = (Game) inputStream.readObject();
                Server.setGame(game);

                player = game.getPlayers().get(game.getPlayers().size() - 1);

            } catch (SocketException e) {
                e.printStackTrace();
            }

            while (!Server.getGame().isHasGameStarted()) {
                Thread.sleep(1000);
            }

            game = Server.getGame();
            for (Player p : game.getPlayers()) {
                if (p.getId().equals(player.getId())) {
                    player = p;
                }
            }
            oldGame = Server.getGame();
            outputStream.writeObject(Server.getGame());
            outputStream.flush();

            while (true) {
                if (player.isTurn() || !Server.getGame().equals(oldGame)) {
                    if (player.isTurn()) {
                        game = (Game) inputStream.readObject();

                        for (Player p : game.getPlayers()) {
                            if (p.getId().equals(player.getId())) {
                                if (p.isTurn()) {
                                    p.setTurn(false);
                                    p.getNextPlayer().setTurn(true);
                                    player = p;
                                    Server.setGame(game);
                                }
                            }
                        }
                    } else if (!Server.getGame().equals(oldGame)) {
                        outputStream.writeObject(Server.getGame());
                        outputStream.flush();
                        oldGame = Server.getGame();

                        for (Player p : Server.getGame().getPlayers()) {
                            if (p.getId().equals(player.getId())) {
                                player = p;
                            }
                        }
                    }
                }
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
