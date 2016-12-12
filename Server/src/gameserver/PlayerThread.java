package gameserver;


import game.Game;
import players.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

/**
 * Thread for client connections
 * <p>
 * Created by Suhas on 10/16/2016.
 */
public class PlayerThread extends Thread {
    private Socket socket;
    private Player player;
    private Game game;
    private UUID gameId;
    private UUID playerId;

    PlayerThread(Socket clientSocket, Game game) {
        this.socket = clientSocket;
        this.game = game;
    }

    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            gameId = game.getGameId();

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

            playerId = player.getId();

            while (!Server.getGame(gameId).hasGameStarted()) {
                Thread.sleep(5000);
            }

            game = Server.getGame(gameId);
            assert game != null;
            outputStream.writeObject(Server.getGame(gameId));
            outputStream.flush();

            while (true) {

                if (Server.getGame(gameId).getPlayer(playerId).isEndTurnUpdate()) {

                    if (!Server.getGame(gameId).hasGameEnded()) {
                        outputStream.writeObject(Server.getGame(gameId));
                        outputStream.flush();

                        game = (Game) inputStream.readObject();
                        game.getPlayer(playerId).setEndTurnUpdate(false);
                        Server.setGame(game);
                    } else {
                        outputStream.writeObject(Server.getGame(gameId));
                        outputStream.flush();
                        break;
                    }
                }
                else{
                    outputStream.writeObject(Server.getGame(gameId));
                    outputStream.flush();
                    Thread.sleep(5000);
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            try {
                socket.close();
                int numExecutions = 0;
                while (!Server.getGame(gameId).hasGameEnded() && numExecutions < 1000) {
                    if(Server.getGame(gameId).getPlayer(playerId).isTurn()) {
                        Server.getGame(gameId).getPlayer(playerId).setTurn(false);
                        Server.getGame(gameId).getPlayer(playerId).getNextPlayer().setTurn(true);
                        Server.getGame(gameId).getPlayer(playerId).setHasSuggested(false);
                        Server.getGame(gameId).getPlayer(playerId).setHasMoved(false);
                        Server.getGame(gameId).getPlayer(playerId).getNextPlayer().setEndTurnUpdate(true);
                        Server.getGame(gameId).getPlayer(playerId).setFirstUpdate(true);
                        Server.getGame(gameId).getPlayer(playerId).setHasFalselyAccused(true);
                        numExecutions++;
                    }
                }
                Server.getGame(gameId).setHasGameEnded();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
