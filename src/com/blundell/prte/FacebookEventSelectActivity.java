package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.blundell.prte.stuff.Event;
import com.blundell.prte.stuff.EventResponseParser;
import com.facebook.Request;
import com.facebook.Response;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class FacebookEventSelectActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select_facebook);

        Toast.makeText(this, "LOADING", 0).show();

        Request request = new Request(ParseFacebookUtils.getSession(), "/me/events");
        request.setCallback(new Request.Callback() {
            @Override
            public void onCompleted(Response response) {
                Log.d("MatchEvent", "/me/events" + response.toString());

                ListView listView = (ListView) findViewById(R.id.select_list);

                final List<Event> events = new EventResponseParser().parse(response);
                listView.setAdapter(
                        new ArrayAdapter<Event>(
                                FacebookEventSelectActivity.this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                events));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Event event = events.get(position);
                        Log.d("MatchEvent", event.getId() + " You Clicked " + event.getName());

                        ParseUser currentUser = ParseUser.getCurrentUser();
                        currentUser.put("SELECTED_EVENT", event);
                        currentUser.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                Intent intent = new Intent(FacebookEventSelectActivity.this, MatchEventWithDeezerHistoryActivity.class);
                                startActivity(intent);

                            }
                        });

                    }
                });

            }
        });
        request.executeAsync();

    }
}
