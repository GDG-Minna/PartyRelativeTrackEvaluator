package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.android.Facebook;
import com.parse.ParseObject;

public class MainActivity extends PrteActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        Log.d("MainActivity", Facebook.TOKEN);
    }

    public void onSignInWithFacebookClick(View button) {
        Intent intent = new Intent(this, SignUpWithFacebookActiivty.class);
        startActivity(intent);
    }

    public void onSelectFacebookEventClick(View button) {
        Intent intent = new Intent(this, FacebookEventSelectActivity.class);
        startActivity(intent);
    }

}
