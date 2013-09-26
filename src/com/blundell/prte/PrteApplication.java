package com.blundell.prte;

import android.app.Application;

import com.blundell.prte.stuff.Event;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

public class PrteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Event.class);
        Parse.initialize(this, "LD2xwuid5SqhYTKjzpLw6PBnCA4qgneiQIsB7a5j", "BxuatralMCzNfkabigd3DVV1XdqlOEZugO9Ap5z9");
        ParseFacebookUtils.initialize(getString(R.string.app_id));
    }
}
