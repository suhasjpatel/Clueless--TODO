package game;

import cards.CardDeck;
import enums.Suspect;
import players.Player;
import rooms.GameMap;
import rooms.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Shared game instance between the client and server.
 *
 * Created by Suhas on 10/16/2016.
 */
public class Game implements Serializable {
    private ArrayList<Player> players = new ArrayList<>();
    private GameMap map = new GameMap();
    private CardDeck cardDeck = new CardDeck();
    private boolean hasGameStarted = false;
    private boolean hasGameEnded = false;
    private Player winner;
    private UUID gameId;
    private String message = "";

    public Game(UUID gameId) {
        this.gameId = gameId;
    }

    public void start() {

        cardDeck.dealCards(players);

        int playerNumber = 0;

        for (Player p : players) {
            p.setCharacter(Suspect.values()[playerNumber]);
            p.setLocation(startingLocation(p.getCharacter()));
            p.setNextPlayer(players.get((players.indexOf(p) + 1) % players.size()));
            playerNumber++;
        }

        System.out.println(cardDeck.getCaseFile());

        getPlayers().get(0).setTurn(true);

        hasGameStarted = true;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private Node startingLocation(Suspect s) {
        switch (s) {
            case MUSTARD:
                return map.getHallway7();
            case SCARLET:
                return map.getHallway6();
            case PLUM:
                return map.getHallway4();
            case GREEN:
                return map.getHallway2();
            case WHITE:
                return map.getHallway1();
            case PEACOCK:
                return map.getHallway3();
            default:
                return null;
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public UUID getGameId() {
        return gameId;
    }
    public GameMap getMap() {
        return map;
    }
    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public boolean hasGameStarted() {
        return hasGameStarted;
    }

    public boolean hasGameEnded() {
        return hasGameEnded;
    }
    public void setHasGameEnded() {
        this.hasGameEnded = true;
    }

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
