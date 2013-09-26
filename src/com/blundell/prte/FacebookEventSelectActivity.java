package com.blundell.prte;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        Request request = new Request(ParseFacebookUtils.getSession(), "/me/events");
        request.setCallback(new Request.Callback() {
            @Override
            public void onCompleted(Response response) {
                Log.d("FEV", response.toString());

                ListView listView = (ListView) findViewById(R.id.select_list);

                List<String> events = new EventResponseParser().parse(response);
                listView.setAdapter(
                        new ArrayAdapter<String>(
                                FacebookEventSelectActivity.this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                events));

            }
        });
        request.executeAsync();

    }
}
