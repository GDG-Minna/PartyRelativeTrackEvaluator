package com.blundell.prte.stuff;

import com.facebook.Response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventResponseParser {

    public List<Event> parse(Response response) {
        List<Event> events = new ArrayList<Event>();
        try {
            JSONArray data = response.getGraphObject().getInnerJSONObject().getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);

                String name = jsonObject.getString("name");
                long id = jsonObject.getLong("id");
                String startTime = jsonObject.getString("start_time");
                String endTime = jsonObject.getString("end_time");

                events.add(new Event().setName(name).setId(id).setStartTime(startTime).setEndTime(endTime));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            events.add(new Event().setName("PARSE FAIL"));
        }
        return events;
    }
}
