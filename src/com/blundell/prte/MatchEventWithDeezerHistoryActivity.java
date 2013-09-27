package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.domain.Event;
import com.blundell.prte.domain.Song;
import com.blundell.prte.stuff.DeezerHistoryParser;
import com.blundell.prte.stuff.SongToEventMatcher;
import com.deezer.sdk.*;
import com.parse.ParseObject;
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
        Log.d("MatchEvent", "Deezer Access: " + deezer_access_token);
        deezerConnect.setAccessToken(this, deezer_access_token);
        deezerConnect.requestAsync(deezerRequest, new RequestListener() {
            @Override
            public void onComplete(String response, Object requestId) {
                popToastOnUiThread("Music History/ COMPLETE");
//                Log.d("MatchEvent", response);

                List<Song> songs = new DeezerHistoryParser().parse(response);
                Event selectedEvent = (Event) ParseUser.getCurrentUser().get("SELECTED_EVENT");
                List<Song> matchedSongs = new SongToEventMatcher(songs).matchOn(selectedEvent);
                for (Song song : matchedSongs) {
                    Log.d("MatchEvent", song.toString());
                }

                ParseObject eventSongs = new ParseObject("EventSongs");
                eventSongs.addAll("songs", matchedSongs);
                eventSongs.saveInBackground();

                Intent intent = new Intent(MatchEventWithDeezerHistoryActivity.this, FindFacebookUsersForEventActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onIOException(IOException e, Object requestId) {
                popToastOnUiThread("Music History/ IO EXc");

            }

            @Override
            public void onMalformedURLException(MalformedURLException e, Object requestId) {
                popToastOnUiThread("Music History/ Maluformed url");

            }

            @Override
            public void onOAuthException(OAuthException e, Object requestId) {
                popToastOnUiThread("Music History/ OAuth Excp");

            }

            @Override
            public void onDeezerError(DeezerError deezerError, Object o) {
                popToastOnUiThread("Music History/ Deezer Error");

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
