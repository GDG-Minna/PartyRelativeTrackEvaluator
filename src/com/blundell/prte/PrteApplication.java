package com.blundell.prte;

import android.app.Application;

import com.parse.Parse;

public class PrteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "LD2xwuid5SqhYTKjzpLw6PBnCA4qgneiQIsB7a5j", "BxuatralMCzNfkabigd3DVV1XdqlOEZugO9Ap5z9");
    }
}
