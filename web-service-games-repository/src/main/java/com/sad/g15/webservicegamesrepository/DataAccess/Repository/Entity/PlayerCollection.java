package com.sad.g15.webservicegamesrepository.DataAccess.Repository.Entity;

import java.util.ArrayList;
import java.util.List;

//This is the Game Players Collection in the Entity Class Diagram. THIS CLASS NEEDS A DB TABLE?
public class PlayerCollection {

    private List<Player> players;

    public PlayerCollection(List<Player> players) {
        this.players = new ArrayList<Player>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "PlayerCollection{" +
                "players=" + players +
                '}';
    }
}
