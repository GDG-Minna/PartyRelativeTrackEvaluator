package com.blundell.prte.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Song")
public class Song extends ParseObject {

    public Song() {
    }

    public int getId() {
        return getInt("id");
    }

    public Song setId(int id) {
        put("id", id);
        return this;
    }

    public String getPlayedAt() {
        return getString("playedAt");
    }

    public Song setPlayedAt(String playedAt) {
        put("playedAt", playedAt);
        return this;
    }

    public String getDuration() {
        return getString("duration");
    }

    public Song setDuration(String duration) {
        put("duration", duration);
        return this;
    }

    public String getArtist() {
        return getString("artist");
    }

    public Song setArtist(String artist) {
        put("artist", artist);
        return this;
    }

    public String getTitle() {
        return getString("title");
    }

    public Song setTitle(String title) {
        put("title", title);
        return this;
    }

    @Override
    public String toString() {
        return getArtist() + " : " + getTitle();
    }
}
