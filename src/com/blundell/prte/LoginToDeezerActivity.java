package com.blundell.prte;

import android.os.Bundle;
import android.widget.Toast;

import com.deezer.sdk.*;

public class LoginToDeezerActivity extends PrteActivity {

    /**
     * Your app Deezer appId.
     */
    public final static String APP_ID = "125011";

    /**
     * Permissions requested on Deezer accounts.
     */
    private final static String[] PERMISSIONS = new String[]{"basic_access", "listening_history"};
    /**
     * DeezerConnect object used for authentification or request.
     */
    private DeezerConnect deezerConnect = new DeezerConnectImpl(APP_ID);

    static String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_deezer);

        deezerConnect.authorize(this, PERMISSIONS, new MyDialogHandler());
    }

    /**
     * Handle DeezerConnect callbacks.
     */
    private class MyDialogHandler implements DialogListener {
        @Override
        public void onComplete(final Bundle values) {
            Toast.makeText(LoginToDeezerActivity.this, "SUCCESS", 0).show();
            access_token = (String) values.get("access_token");

        }//met

        @Override
        public void onDeezerError(final DeezerError deezerError) {
            Toast.makeText(LoginToDeezerActivity.this, "deezer error", 0).show();

        }//met

        @Override
        public void onError(final DialogError dialogError) {
            Toast.makeText(LoginToDeezerActivity.this, "dialog error", 0).show();

        }//met

        @Override
        public void onCancel() {
            Toast.makeText(LoginToDeezerActivity.this, "cancel", 0).show();

        }//met

        @Override
        public void onOAuthException(OAuthException oAuthException) {
            Toast.makeText(LoginToDeezerActivity.this, "Exception", 0).show();

        }//met
    }//inner class
}
