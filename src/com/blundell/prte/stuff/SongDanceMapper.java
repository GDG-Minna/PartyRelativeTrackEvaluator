package com.blundell.prte.stuff;

import com.blundell.prte.domain.Dance;
import com.blundell.prte.domain.DanceStatistics;
import com.blundell.prte.domain.Song;
import com.blundell.prte.domain.User;

import java.util.List;

public class SongDanceMapper {
    private final User user;

    public SongDanceMapper(User user) {
        this.user = user;
    }

    public DanceStatistics map(List<Song> song, List<Dance> dances) {
        return new DanceStatistics(user);
    }
}
