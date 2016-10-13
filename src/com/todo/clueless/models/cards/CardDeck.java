package com.todo.clueless.models.cards;

import com.todo.clueless.models.enums.Card;
import com.todo.clueless.models.enums.Room;
import com.todo.clueless.models.enums.Suspect;
import com.todo.clueless.models.enums.Weapon;
import com.todo.clueless.models.players.CaseFile;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private ArrayList<Card> cardDeck;
    private CaseFile caseFile = new CaseFile();

    public CardDeck() {
        boolean foundSuspect = false;
        boolean foundWeapon = false;
        boolean foundRoom = false;

        //Create a shuffled deck of cards containing all of the suspect, weapon, and rooms cards
        cardDeck = new ArrayList<>();
        for (Suspect suspect : Suspect.values()) {
            cardDeck.add(suspect.toCard());
        }
        for (Weapon weapon : Weapon.values()) {
            cardDeck.add(weapon.toCard());
        }
        for (Room room : Room.values()) {
            cardDeck.add(room.toCard());
        }

        Collections.shuffle(cardDeck);

        //Remove 1 of each card and put them in the case file
        for (Card card : cardDeck) {
            if (!(foundSuspect && foundWeapon && foundRoom)) {
                if (card.isSuspect() && !foundSuspect) {
                    caseFile.setSuspectCard(card);
                    foundSuspect = true;
                } else if (card.isWeapon() && !foundWeapon) {
                    caseFile.setWeaponCard(card);
                    foundWeapon = true;
                } else if (card.isRoom() && !foundRoom) {
                    caseFile.setRoomCard(card);
                    foundRoom = true;
                }
            }
        }
        cardDeck.remove(caseFile.getRoomCard());
        cardDeck.remove(caseFile.getSuspectCard());
        cardDeck.remove(caseFile.getWeaponCard());
    }

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public CaseFile getCaseFile() {
        return caseFile;
    }
}
