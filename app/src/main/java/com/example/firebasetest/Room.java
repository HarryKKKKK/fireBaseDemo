package com.example.firebasetest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String id;
    private List<User> players;
    private final User owner;
    private Game game;
    private User last;
    private boolean isStart = false;

    public Room(Game game, User owner, String id) {
        this.id = id;
        this.game = game;
        this.owner = owner;
        this.players = new ArrayList<>();
        players.add(owner);
    }

    public void addPlayer(User user) {
        if (players.size() < game.getCapacity()) {
            this.players.add(user);
        }
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isStart() {
        return isStart;
    }

    public void printFirstPlayer() {
        Log.d("real", "players has size: " + players.size());
    }

    public void setPlayers(List<User> users) {
        players = users;
    }

    public List<User> getPlayers() {
        return players;
    }

    public boolean hasId(int id) {
        return this.id.equals(id);
    }

    public String getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isOwned(User user) {
        return owner == user;
    }
    public User getOwner() {
        return owner;
    }

    public User getLast() {
        return last;
    }
}
