package com.blundell.prte.domain;

import java.util.HashMap;
import java.util.Map;

public class DanceStatistics {
    User user;
    Map<Song, Statistics> statisticsMap = new HashMap<Song, Statistics>();

    private class Statistics {
        long lengthDancedFor;
        int caloriesBurned;
        double intensity;
    }

    public boolean hasDancedMore(Song song, DanceStatistics competitor) {
        return getStatistics(song).lengthDancedFor > competitor.getStatistics(song).lengthDancedFor;
    }

    public boolean hasBurntMore(Song song, DanceStatistics competitor) {
        return getStatistics(song).caloriesBurned > competitor.getStatistics(song).caloriesBurned;
    }

    public boolean hasDancedHarder(Song song, DanceStatistics competitor) {
        return getStatistics(song).intensity > competitor.getStatistics(song).intensity;
    }

    private Statistics getStatistics(Song song) {
        return statisticsMap.get(song);
    }
}
