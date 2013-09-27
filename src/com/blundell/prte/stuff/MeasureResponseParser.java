package com.blundell.prte.stuff;

import com.blundell.prte.domain.Movement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MeasureResponseParser {

    /**
     * vasistas
     */
    // 36 Number of steps in timeframe
    // 37 elevation in metres
    // 38 calories burned                      < this
    // 39intensity                             < this
    // 40 distance in metres                   < this
    // 41 descent in metres
    // 42 subcategory 0 inctive, 1 walk, 2 run
    public List<Movement> parse(String response) {
        List<Movement> movements = new ArrayList<Movement>();

        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("body").getJSONArray("series").getJSONObject(0);
            JSONArray timesArray = jsonObject.getJSONArray("dates");
            JSONArray typesArray = jsonObject.getJSONArray("types");
            JSONArray dataArray = jsonObject.getJSONArray("vasistas");

            for (int i = 0; i < timesArray.length(); i++) {


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movements;
    }
}
