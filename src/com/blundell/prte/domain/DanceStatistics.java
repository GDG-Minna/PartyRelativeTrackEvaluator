package com.blundell.prte.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DanceStatistics {
    User user;
    Map<Song, Statistics> statisticsMap = new HashMap<Song, Statistics>();

    public DanceStatistics(User user) {
        this.user = user;
    }

    public boolean hasDancedMore(Song song, DanceStatistics competitor) {
        return getStatistics(song).distanceCovered > competitor.getStatistics(song).distanceCovered;
    }

    public boolean hasBurntMore(Song song, DanceStatistics competitor) {
        return getStatistics(song).caloriesBurned > competitor.getStatistics(song).caloriesBurned;
    }

    public boolean hasDancedHarder(Song song, DanceStatistics competitor) {
        return getStatistics(song).intensity > competitor.getStatistics(song).intensity;
    }

    public Statistics getStatistics(Song song) {
        return statisticsMap.get(song);
    }

    public DanceStatistics add(Song song, Statistics statistics) {
        statisticsMap.put(song, statistics);
        return this;
    }

    public Set<Song> getSongs() {
        return statisticsMap.keySet();
    }

    public Collection<Statistics> allStats() {
        return statisticsMap.values();
    }

    public User getUser() {
        return user;
    }
}
