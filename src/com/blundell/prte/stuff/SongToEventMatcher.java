package com.blundell.prte.stuff;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SongToEventMatcher {
    private final List<Song> songs;
    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

    public SongToEventMatcher(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> matchOn(Event event) {
        List<Song> matchedSongs = new ArrayList<Song>();

//        for (Song song : songs) {
//            try {
//                TimeZone tz = TimeZone.getTimeZone("UTC");
//                DF.setTimeZone(tz);
//                Date eventStartDate = DF.parse(event.getStartTime());
//                Date eventEndDate = DF.parse(event.getEndTime());
//
//                Date songStartDate = new Date(new Integer(song.getPlayedAt()));
//
//                if (songStartDate.after(eventStartDate) && songStartDate.before(eventEndDate)) {
//                    matchedSongs.add(song);
//                }
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }

        // FAKE DATA
        if (songs.size() > 2) {
            matchedSongs.add(songs.get(0));
            matchedSongs.add(songs.get(1));
        }
        if (songs.size() > 4) {
            matchedSongs.add(songs.get(2));
            matchedSongs.add(songs.get(3));
        }

        return matchedSongs;
    }
}
