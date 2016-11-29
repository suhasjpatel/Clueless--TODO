package com.todo.clueless.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Suhas on 10/16/2016.
 */
public class Server {
    private static final int PORT = 22222;
    private static Game game = new Game();


    public static void main(String args[]) {
        ServerSocket serverSocket;
        Socket socket = null;
        int x = 0;

        try {
            serverSocket = new ServerSocket(PORT);

            while (x < 2) {
                try {
                    socket = serverSocket.accept();
                    x++;
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }

                PlayerThread thread = new PlayerThread(socket);
                thread.start();
            }

            Thread.sleep(5000);

            game.start();

            while (!serverSocket.isClosed()) {
            }

        } catch (IOException e) {
            System.err.println("Unable to make server socket with port.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized static Game getGame() {
        return game;
    }

    synchronized static void setGame(Game game) {
        Server.game = game;
    }

}
