package com.blundell.prte;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.domain.Event;
import com.parse.ParseUser;

public class StatisticsActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Event selectedEvent = (Event) ParseUser.getCurrentUser().get("SELECTED_EVENT");
        TextView titleTextView = (TextView) findViewById(R.id.stats_event_name);
        titleTextView.setText(selectedEvent.getName());

        TextView dancedMostTextView = (TextView) findViewById(R.id.stats_who_dance_most);
        TextView topSongTextView = (TextView) findViewById(R.id.stats_song_most_danced_to);
        TextView leastActivePersonTextView = (TextView) findViewById(R.id.stats_least_active_person);

    }

    public void onShareWithFacebookClick(View button) {

    }
}
