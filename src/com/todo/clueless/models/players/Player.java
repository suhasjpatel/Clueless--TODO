package com.todo.clueless.models.players;
import com.todo.clueless.models.enums.Suspect;

public class Player {
    private Suspect player;
    private String name;

    public Player(Suspect player, String name) {
        this.player = player;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Suspect getPlayer() {
        return player;
    }

    public void setPlayer(Suspect player) {
        this.player = player;
    }
}
