package com.blundell.prte.stuff;

import com.blundell.prte.domain.Song;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeezerHistoryParser {
    public List<Song> parse(String response) {
        List<Song> songs = new ArrayList<Song>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray songsData = jsonObject.getJSONArray("data");
            for (int i = 0; i < songsData.length(); i++) {
                JSONObject songObject = songsData.getJSONObject(i);

                int id = songObject.getInt("id");
                String playedAt = songObject.getString("timestamp");
                String duration = songObject.getString("duration");
                String artist = songObject.getJSONObject("artist").getString("name");
                String title = songObject.getString("title");

                songs.add(new Song().setId(id)
                        .setPlayedAt(playedAt)
                        .setDuration(duration)
                        .setArtist(artist)
                        .setTitle(title));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return songs;
    }
}
