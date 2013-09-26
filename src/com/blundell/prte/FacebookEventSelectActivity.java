package com.blundell.prte;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.facebook.Request;
import com.facebook.Response;
import com.parse.ParseFacebookUtils;

public class FacebookEventSelectActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select_facebook);

        Request request = new Request(ParseFacebookUtils.getSession(), "/me/events");
        request.setCallback(new Request.Callback() {
            @Override
            public void onCompleted(Response response) {
                Log.d("FEV", response.toString());
                ListView listView = (ListView) findViewById(R.id.select_list);



            }
        });
        request.executeAsync();

    }
}
