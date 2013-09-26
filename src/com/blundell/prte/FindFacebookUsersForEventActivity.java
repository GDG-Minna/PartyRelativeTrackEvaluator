package com.blundell.prte;

import android.os.Bundle;
import android.util.Log;

import com.blundell.prte.stuff.Event;
import com.facebook.Request;
import com.facebook.Response;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class FindFacebookUsersForEventActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_facebook_users_for_event);

        Event selectedEvent = (Event) ParseUser.getCurrentUser().get("SELECTED_EVENT");
        Log.d("MatchEvent", "Finding users for " + selectedEvent + " " + selectedEvent.getId());

        Request request = new Request(ParseFacebookUtils.getSession(), selectedEvent.getId() + "/invited");
        request.setCallback(new Request.Callback() {
            @Override
            public void onCompleted(Response response) {
                Log.d("MatchEvent", "/invited: " + response.toString());

            }
        });
        request.executeAsync();
    }
}
