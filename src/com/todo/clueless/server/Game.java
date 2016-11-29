package com.todo.clueless.server;

import com.todo.clueless.shared.models.cards.CardDeck;
import com.todo.clueless.shared.models.enums.Suspect;
import com.todo.clueless.shared.models.players.Player;
import com.todo.clueless.shared.models.rooms.GameMap;
import com.todo.clueless.shared.models.rooms.Node;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Suhas on 10/16/2016.
 */
public class Game implements Serializable {
    private ArrayList<Player> players = new ArrayList<>();
    private GameMap map = new GameMap();
    private CardDeck cardDeck = new CardDeck();
    private boolean hasGameStarted = false;
    private int turnNumber = 0;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() {
        cardDeck.dealCards(players);

        int playerNumber = 0;

        for (Player p : players) {
            //Removes a player's cards from notebook, needed to bluff though.
            /*for(Card c : p.getCards()){
                if (c.isRoom()){
                    p.getNotebook().getPossibleRooms().remove(c.toRoom());
                }
                else if(c.isSuspect()) {
                    p.getNotebook().getPossibleSuspects().remove(c.toSuspect());
                }
                else if(c.isWeapon()) {
                    p.getNotebook().getPossibleWeapons().remove(c.toWeapon());
                }
            }*/

            p.setCharacter(Suspect.values()[playerNumber]);
            p.setLocation(startingLocation(p.getCharacter()));
            p.setNextPlayer(players.get((players.indexOf(p) + 1) % players.size()));
            playerNumber++;
        }

        System.out.println(cardDeck.getCaseFile());

        getPlayers().get(0).setHost(true);
        getPlayers().get(0).setTurn(true);

        hasGameStarted = true;
    }

    public Node startingLocation(Suspect s) {
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

    public boolean isHasGameStarted() {
        return hasGameStarted;
    }

    public void setHasGameStarted(boolean hasGameStarted) {
        this.hasGameStarted = hasGameStarted;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }


    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
}
