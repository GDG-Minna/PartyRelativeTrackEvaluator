package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.base.PrteApplication;
import com.blundell.prte.domain.Event;
import com.blundell.prte.domain.User;
import com.blundell.prte.stuff.UserListResponseParser;
import com.facebook.Request;
import com.facebook.Response;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.List;

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

                List<User> userList = new UserListResponseParser().parse(response);

                for (User user : userList) {
                    Log.d("MatchEvent", "(FB) Found user : " + user.toString());
                }

                PrteApplication.facebookUserList.addAll(userList);

                Intent intent = new Intent(FindFacebookUsersForEventActivity.this, FindWithingsUsersActivity.class);
                startActivity(intent);
                finish();
            }
        });
        request.executeAsync();
    }
}
