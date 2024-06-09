package com.example.firebasetest;

public class Game {
    private static final int MAX_CAP = 4;

    private final String name;
    private final String place;
    private final String info;
    private final int image;
    private final int capacity;
    private final int SCREEN_WIDTH_COUNT = 30;


    private Game(Builder builder) {
        this.name = builder.name;
        this.place = builder.place;
        this.info = builder.info;
        this.image = builder.image;
        this.capacity = builder.capacity;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getInfo() {
        return info;
    }

    public String getInfo(Boolean useInSearchPage) {
        if (useInSearchPage && info.length() > SCREEN_WIDTH_COUNT) {
            return info.substring(0, SCREEN_WIDTH_COUNT) + "...";
        }
        return info;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean holdAt(String place) {
        return this.place.toLowerCase().contains(place.toLowerCase()) || this.place.equals("@Anywhere");
    }

    public boolean hasName(String name) {
        return this.name.toLowerCase().contains(name.toLowerCase()) || this.name.equals("@Untitled");
    }

    public static class Builder {
        private String name = "@Untitled";
        private String place = "@Anywhere";
        private String info = "No description yet";
        private int image = 0;
        private int capacity = MAX_CAP;

        public Builder withCapacity(int capacity) {
            assert capacity <= MAX_CAP;
            this.capacity = capacity;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPlace(String place) {
            this.place = place;
            return this;
        }

        public Builder withInfo(String info) {
            this.info = info;
            return this;
        }

        public Builder withImage(int image) {
            this.image = image;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
