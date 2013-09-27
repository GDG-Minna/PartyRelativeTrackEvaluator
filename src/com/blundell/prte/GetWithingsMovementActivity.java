package com.blundell.prte;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.base.PrteApplication;
import com.blundell.prte.domain.DanceStatistics;
import com.blundell.prte.domain.Movement;
import com.blundell.prte.domain.Song;
import com.blundell.prte.domain.User;
import com.blundell.prte.stuff.MeasureResponseParser;
import com.blundell.prte.stuff.SongDanceMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetWithingsMovementActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_withings_movement);

        // query withings for movement

        new AsyncTask<String, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(String... params) {
                List<User> userList = PrteApplication.intersectionUserList;
//                Event selectedEvent = (Event) ParseUser.getCurrentUser(). get("SELECTED_EVENT");
//                WithingsAcc withingsAcc = (WithingsAcc) ParseUser.getCurrentUser().get("WITHINGS_ACC");
//                try {
//                    String uri = "https://hackathon-api.withings.com/v2/measure?action=getwamhf" +
//                            "&userid=" + user.getWithingsId() +
//                            "&sessionid=" + withingsAcc.getSessionId();
////                    +
////                            "&startdate=" + selectedEvent.getStartTime() +
////                            "&enddate=" + selectedEvent.getEndTime();
//
//                    Log.d("MatchEvent", "measure URI: " + uri);
//
//                    HttpGet get = new HttpGet(uri);
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpResponse httpResponse = httpClient.execute(get);
//                    String jsonString = convertStreamToString(httpResponse.getEntity().getContent());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                List<Song> songsForEventList = PrteApplication.songsForEventList;
                // API FAIL - using stub data
                List<DanceStatistics> danceStatisticsList = new ArrayList<DanceStatistics>();
                for (int i = 0; i < userList.size(); i++) {
                    String response = stubJsonResponses.size() <= i ? stubJsonResponses.get(0) : stubJsonResponses.get(i);
                    Log.d("MatchEvent", "measure Response " + response);
                    List<Movement> movements = new MeasureResponseParser().parse(response);
                    DanceStatistics danceStatistics = new SongDanceMapper(userList.get(i)).map(songsForEventList, movements);
                    danceStatisticsList.add(danceStatistics);
                }
                PrteApplication.danceStatisticsList.clear();
                PrteApplication.danceStatisticsList.addAll(danceStatisticsList);

                return null;
            }

//            String convertStreamToString(InputStream is) {
//                Scanner s = new Scanner(is).useDelimiter("\\A");
//                return s.hasNext() ? s.next() : "";
//            }

            @Override
            protected void onPostExecute(List<String> strings) {
                super.onPostExecute(strings);

                // Intent to go to final scoreboard screen
                Intent intent = new Intent(GetWithingsMovementActivity.this, StatisticsActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(GetWithingsMovementActivity.this, "Retrieved Guests Movements - SUCCESS", 0).show();
            }
        }.execute("");
    }

    List<String> stubJsonResponses = Arrays.asList(jsonUserOneString, jsonUserTwoString, jsonUserThreeString);

    public static String jsonUserOneString = "{\n" +
            "    \"status\": 0,\n" +
            "    \"body\": {\n" +
            "        \"series\": [\n" +
            "            {\n" +
            "                \"dates\": [ 1365082428, 1365083208, 1365083268, 1365083328 ],\n" +
            "                \"format\": \"byvasistas\",\n" +
            "                \"types\": [ 42, 36, 37, 41, 38, 39, 40 ],\n" +
            "                \"vasistas\": [\n" +
            "                    [ 1, 21, 0, 0, 0, 90.760002, 15.46 ],\n" +
            "                    [ 1, 42, 0, 0, 2, 242.740005, 35.349998 ],\n" +
            "                    [ 1, 51, 0.98, 0, 2, 239.639999, 38.610001 ],\n" +
            "                    [ 1, 4, 0, 0, 0, 14.12, 2.72 ]\n" +
            "                ],\n" +
            "                \"tempGrpId\": \"1366031185_19b6\",\n" +
            "                \"meastime\": 1366031185,\n" +
            "                \"deviceid\": 564523\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    public static String jsonUserTwoString = "{\n" +
            "    \"status\": 0,\n" +
            "    \"body\": {\n" +
            "        \"series\": [\n" +
            "            {\n" +
            "                \"dates\": [ 1365082428, 1365083208, 1365083268, 1365083328 ],\n" +
            "                \"format\": \"byvasistas\",\n" +
            "                \"types\": [ 42, 36, 37, 41, 38, 39, 40 ],\n" +
            "                \"vasistas\": [\n" +
            "                    [ 1, 21, 0, 0, 0, 90.760002, 15.46 ],\n" +
            "                    [ 1, 42, 0, 0, 2, 242.740005, 35.349998 ],\n" +
            "                    [ 1, 51, 0.98, 0, 2, 239.639999, 38.610001 ],\n" +
            "                    [ 1, 4, 0, 0, 0, 14.12, 2.72 ]\n" +
            "                ],\n" +
            "                \"tempGrpId\": \"1366031185_19b6\",\n" +
            "                \"meastime\": 1366031185,\n" +
            "                \"deviceid\": 564523\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    public static String jsonUserThreeString = "{\n" +
            "    \"status\": 0,\n" +
            "    \"body\": {\n" +
            "        \"series\": [\n" +
            "            {\n" +
            "                \"dates\": [ 1365082428, 1365083208, 1365083268, 1365083328 ],\n" +
            "                \"format\": \"byvasistas\",\n" +
            "                \"types\": [ 42, 36, 37, 41, 38, 39, 40 ],\n" +
            "                \"vasistas\": [\n" +
            "                    [ 1, 21, 0, 0, 0, 90.760002, 15.46 ],\n" +
            "                    [ 1, 42, 0, 0, 2, 242.740005, 35.349998 ],\n" +
            "                    [ 1, 51, 0.98, 0, 2, 239.639999, 38.610001 ],\n" +
            "                    [ 1, 4, 0, 0, 0, 14.12, 2.72 ]\n" +
            "                ],\n" +
            "                \"tempGrpId\": \"1366031185_19b6\",\n" +
            "                \"meastime\": 1366031185,\n" +
            "                \"deviceid\": 564523\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
}
