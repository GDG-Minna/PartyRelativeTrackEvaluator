package com.blundell.prte.stuff;

import com.blundell.prte.domain.DanceStatistics;
import com.blundell.prte.domain.Movement;
import com.blundell.prte.domain.Song;
import com.blundell.prte.domain.User;

import java.util.List;

public class SongDanceMapper {
    private final User user;

    public SongDanceMapper(User user) {
        this.user = user;
    }

    public DanceStatistics map(List<Song> songs, List<Movement> movements) {
        DanceStatistics danceStatistics = new DanceStatistics(user);

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            String playedAt = song.getPlayedAt();
            for (Movement movement : movements) {
                long startTime = movement.getStartTime();
                // compare - For each song see if there is a movement that matches the timeframe

                // if yes add to the DanceStatistics
            }
            // For now just add each movement to a song
            Movement movement = movements.size() <= i ? movements.get(0) : movements.get(i);
            danceStatistics.add(song, movement.getStatistics());
        }
        return danceStatistics;
    }
}
