package com.blundell.prte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignInWithFacebookClick(View button) {
        Intent intent = new Intent(this, SignUpWithFacebookActiivty.class);
        startActivity(intent);
    }

}
