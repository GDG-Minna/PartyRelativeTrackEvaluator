package com.blundell.prte;

import android.os.Bundle;
import android.util.Log;

import com.blundell.prte.base.PrteActivity;
import com.blundell.prte.base.PrteApplication;
import com.blundell.prte.domain.User;

import java.util.ArrayList;
import java.util.List;

public class JoinUsersActivity extends PrteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_users);

        List<User> intermediateList = new ArrayList<User>();
        for (User wUser : PrteApplication.withingsUserList) {
            for (User fUser : PrteApplication.facebookUserList) {
                String fName = fUser.getName();
                String wName = wUser.getName();

                if (fName.equals(wName)) {
                    fUser.setWithingsId(wUser.getWithingsId());
                    intermediateList.add(fUser);
                }

            }
        }
        PrteApplication.intersectionUserList.addAll(intermediateList);
        for (User user : intermediateList) {
            Log.d("MatchEvent", "Match on Event & Withings: " + user.toString());
        }
    }
}
