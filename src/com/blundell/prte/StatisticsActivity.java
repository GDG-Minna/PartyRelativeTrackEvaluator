package com.blundell.prte;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.base.PrteApplication;
import com.blundell.prte.domain.DanceStatistics;
import com.blundell.prte.domain.Event;
import com.blundell.prte.domain.Song;
import com.blundell.prte.domain.Statistics;
import com.facebook.widget.FacebookDialog;
import com.parse.ParseUser;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class StatisticsActivity extends PrteActivity {

    private String personDancedMostName;
    private String topSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Event selectedEvent = (Event) ParseUser.getCurrentUser().get("SELECTED_EVENT");
        TextView titleTextView = (TextView) findViewById(R.id.stats_event_name);
        titleTextView.setText(selectedEvent.getName());

        List<DanceStatistics> danceStatisticsList = PrteApplication.danceStatisticsList;

        TextView dancedMostTextView = (TextView) findViewById(R.id.stats_who_dance_most);
        personDancedMostName = whoDancedTheMost(danceStatisticsList);
        dancedMostTextView.setText(personDancedMostName);

        TextView topSongTextView = (TextView) findViewById(R.id.stats_song_most_danced_to);
        topSong = whatSongWasDancedToTheMost(danceStatisticsList);
        topSongTextView.setText(topSong);

        TextView leastActivePersonTextView = (TextView) findViewById(R.id.stats_least_active_person);
        String leastActivePersonName = whoWasLeastActive(danceStatisticsList);

    }

    private String whoDancedTheMost(List<DanceStatistics> danceStatisticsList) {
        String name = "Nobody!";
        long biggestDistance = 0;
        for (DanceStatistics danceStats : danceStatisticsList) {
            Collection<Statistics> statistics = danceStats.allStats();
            long totalDistance = 0;
            for (Statistics stats : statistics) {
                totalDistance += stats.getDistanceCovered();
            }
            if (totalDistance > biggestDistance) {
                name = danceStats.getUser().getName();
                biggestDistance = totalDistance;
            }
        }
        return name;
    }

    private String whatSongWasDancedToTheMost(List<DanceStatistics> danceStatisticsList) {
        Song mostDancedSong = null;
        for (DanceStatistics danceStats : danceStatisticsList) {
            long distance = 0;
            Set<Song> songs = danceStats.getSongs();
            for (Song song : songs) {
                Statistics statistics = danceStats.getStatistics(song);
                if (statistics.getDistanceCovered() > distance) {
                    mostDancedSong = song;
                }
            }
        }
        return (mostDancedSong == null) ? "All of them (or none)!" : mostDancedSong.toString();
    }

    private String whoWasLeastActive(List<DanceStatistics> danceStatisticsList) {
        return "Paul Blundell";
    }

    public void onShareWithFacebookClick(View button) {
        new FacebookDialog.ShareDialogBuilder(this)
                .setCaption("Loved the Event, " + personDancedMostName +
                        " danced the most out of everyone " +
                        "and everyone was dancing to " + topSong)
                .build().present();

    }
}
