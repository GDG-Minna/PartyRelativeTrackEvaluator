package com.blundell.prte.base;

import android.app.Application;

import com.blundell.prte.R;
import com.blundell.prte.domain.*;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class PrteApplication extends Application {

    public static List<User> facebookUserList = new ArrayList<User>();
    public static List<User> withingsUserList = new ArrayList<User>();
    public static List<User> intersectionUserList = new ArrayList<User>();
    public static List<Song> songsForEventList = new ArrayList<Song>();
    public static List<DanceStatistics> danceStatisticsList = new ArrayList<DanceStatistics>();

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Song.class);
        ParseObject.registerSubclass(Event.class);
        ParseObject.registerSubclass(WithingsAcc.class);
        Parse.initialize(this, "LD2xwuid5SqhYTKjzpLw6PBnCA4qgneiQIsB7a5j", "BxuatralMCzNfkabigd3DVV1XdqlOEZugO9Ap5z9");
        ParseFacebookUtils.initialize(getString(R.string.app_id));
    }
}
