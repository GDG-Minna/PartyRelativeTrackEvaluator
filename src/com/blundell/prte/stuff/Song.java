package com.blundell.prte.stuff;

public class Song {
    int id;
    String playedAt;
    String duration;
    String artist;
    String title;

    public int getId() {
        return id;
    }

    public Song setId(int id) {
        this.id = id;
        return this;
    }

    public String getPlayedAt() {
        return playedAt;
    }

    public Song setPlayedAt(String playedAt) {
        this.playedAt = playedAt;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public Song setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public Song setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return artist + " : " + title;
    }
}
