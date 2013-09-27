package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blundell.prte.base.PrteActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;

public class LoginWithFacebookActiivty extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_facebook);

        ParseFacebookUtils.logIn(Arrays.asList(ParseFacebookUtils.Permissions.User.EVENTS), this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    finish();
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

}
