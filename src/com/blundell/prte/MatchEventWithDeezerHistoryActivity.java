package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blundell.prte.stuff.DeezerHistoryParser;
import com.blundell.prte.stuff.Event;
import com.blundell.prte.stuff.Song;
import com.blundell.prte.stuff.SongToEventMatcher;
import com.deezer.sdk.*;
import com.parse.ParseUser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class MatchEventWithDeezerHistoryActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_deezer_history);

        DeezerRequest deezerRequest = new DeezerRequest("user/340003261/history");

        DeezerConnect deezerConnect = new DeezerConnectImpl(this, LoginToDeezerActivity.APP_ID);
        String deezer_access_token = ParseUser.getCurrentUser().getString("DEEZER_ACCESS_TOKEN");
        Log.d("MatchEvent", "Access: " + deezer_access_token);
        deezerConnect.setAccessToken(this, deezer_access_token);
        deezerConnect.requestAsync(deezerRequest, new RequestListener() {
            @Override
            public void onComplete(String response, Object requestId) {
                popToastOnUiThread("COMPLETE");
//                Log.d("MatchEvent", response);

                List<Song> songs = new DeezerHistoryParser().parse(response);
                Event selectedEvent = (Event) ParseUser.getCurrentUser().get("SELECTED_EVENT");
                List<Song> matchedSongs = new SongToEventMatcher(songs).matchOn(selectedEvent);
                for (Song song : matchedSongs) {
                    Log.d("MatchEvent", song.toString());
                }

                Intent intent = new Intent(MatchEventWithDeezerHistoryActivity.this, FindFacebookUsersForEventActivity.class);
                startActivity(intent);
            }

            @Override
            public void onIOException(IOException e, Object requestId) {
                popToastOnUiThread("IO EXc");

            }

            @Override
            public void onMalformedURLException(MalformedURLException e, Object requestId) {
                popToastOnUiThread("Maluformed url");

            }

            @Override
            public void onOAuthException(OAuthException e, Object requestId) {
                popToastOnUiThread("OAuth Excp");

            }

            @Override
            public void onDeezerError(DeezerError deezerError, Object o) {
                popToastOnUiThread("Deezer Error");

            }
        });

    }

    private void popToastOnUiThread(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MatchEventWithDeezerHistoryActivity.this, msg, 0).show();
            }
        });
    }
}
