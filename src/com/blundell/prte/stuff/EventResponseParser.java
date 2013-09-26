package com.blundell.prte.stuff;

import com.facebook.Response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventResponseParser {

    public List<String> parse(Response response) {
        List<String> events = new ArrayList<String>();
        try {
            JSONArray data = response.getGraphObject().getInnerJSONObject().getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);

                String name = jsonObject.getString("name");

                events.add(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            events.add("PARSE FAIL");
        }
        return events;
    }
}
