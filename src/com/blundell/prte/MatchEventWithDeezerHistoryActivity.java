package com.blundell.prte;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.deezer.sdk.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class MatchEventWithDeezerHistoryActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_deezer_history);

        DeezerRequest deezerRequest = new DeezerRequest("user/340003261/history");

        DeezerConnect deezerConnect = new DeezerConnectImpl(this, LoginToDeezerActivity.APP_ID);
        deezerConnect.setAccessToken(this, LoginToDeezerActivity.access_token);
        deezerConnect.requestAsync(deezerRequest, new RequestListener() {
            @Override
            public void onComplete(String response, Object requestId) {
                popToastOnUiThread("COMPLETE");
                Log.d("MatchEvent", response);
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
