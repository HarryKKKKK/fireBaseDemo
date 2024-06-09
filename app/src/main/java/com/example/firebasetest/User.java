package com.example.firebasetest;

public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    private int profile;

    public User(String name, int profile) {
        this.name = name;
        this.profile = profile;
    }
}
