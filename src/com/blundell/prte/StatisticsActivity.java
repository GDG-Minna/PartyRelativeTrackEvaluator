package com.blundell.prte;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.base.PrteApplication;
import com.blundell.prte.domain.DanceStatistics;
import com.blundell.prte.domain.Event;
import com.blundell.prte.domain.Statistics;
import com.parse.ParseUser;

import java.util.Collection;
import java.util.List;

public class StatisticsActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Event selectedEvent = (Event) ParseUser.getCurrentUser().get("SELECTED_EVENT");
        TextView titleTextView = (TextView) findViewById(R.id.stats_event_name);
        titleTextView.setText(selectedEvent.getName());

        List<DanceStatistics> danceStatisticsList = PrteApplication.danceStatisticsList;

        TextView dancedMostTextView = (TextView) findViewById(R.id.stats_who_dance_most);
        String name = whoDancedTheMost(danceStatisticsList);
        dancedMostTextView.setText(name);

        TextView topSongTextView = (TextView) findViewById(R.id.stats_song_most_danced_to);
        TextView leastActivePersonTextView = (TextView) findViewById(R.id.stats_least_active_person);

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

    public void onShareWithFacebookClick(View button) {

    }
}
