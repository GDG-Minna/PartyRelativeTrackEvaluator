package com.blundell.prte.stuff;

import com.blundell.prte.domain.User;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LeaderboardResponseParser {
    public List<User> parse(String jsonString) {
        List<User> users = new ArrayList<User>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray bodyArray = jsonObject.getJSONArray("body");

            for (int i = 0; i < bodyArray.length(); i++) {
                JSONObject userObject = bodyArray.getJSONObject(i);
                String firstname = userObject.getString("firstname");
                String lastname = userObject.getString("lastname");
                String name = firstname + " " + lastname;
                String id = userObject.getString("userid");

                users.add(new User().setName(name).setWithingsId(id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }
}
