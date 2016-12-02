package players;

import enums.Card;

import java.io.Serializable;

public class CaseFile implements Serializable {
    private Card weaponCard;
    private Card roomCard;
    private Card suspectCard;

    public Card getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(Card weaponCard) {
        this.weaponCard = weaponCard;
    }

    public Card getRoomCard() {
        return roomCard;
    }

    public void setRoomCard(Card roomCard) {
        this.roomCard = roomCard;
    }

    public Card getSuspectCard() {
        return suspectCard;
    }

    public void setSuspectCard(Card suspectCard) {
        this.suspectCard = suspectCard;
    }

    @Override
    public String toString() {
        return "CaseFile{" +
                "weaponCard=" + weaponCard +
                ", roomCard=" + roomCard +
                ", suspectCard=" + suspectCard +
                '}';
    }
}
