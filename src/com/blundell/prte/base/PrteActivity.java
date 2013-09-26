package com.blundell.prte.base;

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseAnalytics;

public class PrteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpened(getIntent());
    }
}
