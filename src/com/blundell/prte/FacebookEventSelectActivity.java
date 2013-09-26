package com.blundell.prte;

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
import com.parse.ParseFacebookUtils;

import java.util.List;

public class FacebookEventSelectActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select_facebook);

        Toast.makeText(this, "LOADING", 1).show();

        Request request = new Request(ParseFacebookUtils.getSession(), "/me/events");
        request.setCallback(new Request.Callback() {
            @Override
            public void onCompleted(Response response) {
                Log.d("FEV", response.toString());

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
                        Toast.makeText(getApplicationContext(), event.getId() + " You Clicked " + event.getName(), 1).show();
                    }
                });

            }
        });
        request.executeAsync();

    }
}
