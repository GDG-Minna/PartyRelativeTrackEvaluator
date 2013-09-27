package com.blundell.prte.stuff;

import com.blundell.prte.domain.User;
import com.facebook.Response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserListResponseParser {
    public List<User> parse(Response response) {
        List<User> users = new ArrayList<User>();
        try {
            JSONArray userArray = response.getGraphObject().getInnerJSONObject().getJSONArray("data");

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userObject = (JSONObject) userArray.get(i);

                String name = userObject.getString("name");
                String id = userObject.getString("id");

                users.add(new User().setName(name).setFacebookId(id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }
}
