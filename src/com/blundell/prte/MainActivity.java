package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blundell.prte.base.PrteActivity;

public class MainActivity extends PrteActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignInWithFacebookClick(View button) {
        Intent intent = new Intent(this, LoginWithFacebookActiivty.class);
        startActivity(intent);
    }

    public void onSignInWithDeezerClick(View button) {
        Intent intent = new Intent(this, LoginToDeezerActivity.class);
        startActivity(intent);
    }

    public void onSignInWithWithingsClick(View button) {
        Intent intent = new Intent(this, LoginToWithingsActivity.class);
        startActivity(intent);
    }

    public void onSelectFacebookEventClick(View button) {
        Intent intent = new Intent(this, FacebookEventSelectActivity.class);
        startActivity(intent);
    }

}
