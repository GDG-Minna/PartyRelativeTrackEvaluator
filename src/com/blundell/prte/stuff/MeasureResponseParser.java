package com.blundell.prte.stuff;

import com.blundell.prte.domain.Dance;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MeasureResponseParser {
    public List<Dance> parse(String response) {
        List<Dance> dances = new ArrayList<Dance>();

        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("body").getJSONArray("series").getJSONObject(0);
            JSONArray timesArray = jsonObject.getJSONArray("dates");
            JSONArray typesArray = jsonObject.getJSONArray("types");
            JSONArray dataArray = jsonObject.getJSONArray("vasistas");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dances;
    }
}
