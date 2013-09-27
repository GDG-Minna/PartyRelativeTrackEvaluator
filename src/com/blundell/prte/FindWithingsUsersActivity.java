package com.blundell.prte;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.domain.User;
import com.blundell.prte.domain.WithingsAcc;
import com.blundell.prte.stuff.LeaderboardResponseParser;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class FindWithingsUsersActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_withings);

        new AsyncTask<String, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(String... params) {
                WithingsAcc withingsAcc = (WithingsAcc) ParseUser.getCurrentUser().get("WITHINGS_ACC");
                try {

//                    String uri = "https://hackathon-api.withings.com/user?action=getbyuserid" +
//                            "&userid=" + withingsAcc.getUserId() +
//                            "&sessionid=" + withingsAcc.getSessionId();

                    String uri = "http://hackathon-api.withings.com/v2/leaderboard?action=getuserslist" +
                            "&userid=" + withingsAcc.getUserId() +
                            "&sessionid=" + withingsAcc.getSessionId();

                    Log.d("MatchEvent", "getuserslist URI: " + uri);

                    HttpGet get = new HttpGet(uri);
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpResponse httpResponse = httpClient.execute(get);

                    String jsonString = convertStreamToString(httpResponse.getEntity().getContent());
                    Log.d("MatchEvent", "getuserslist Response " + jsonString);

                    List<User> userList = new LeaderboardResponseParser().parse(jsonString);

                    for (User user : userList) {
                        Log.d("MatchEvent", "(WS) Found user : " + user.toString());
                    }

                    ParseObject intersectionUsers = new ParseObject("IntersectionUsers");
                    intersectionUsers.addAll("users", userList);
                    intersectionUsers.saveInBackground();



                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            String convertStreamToString(InputStream is) {
                Scanner s = new Scanner(is).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                super.onPostExecute(strings);

                Toast.makeText(FindWithingsUsersActivity.this, "SUCCESS", 0).show();
            }
        }.execute("");

    }
}
