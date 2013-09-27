package com.blundell.prte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
        PrteApplication.withingsUserList.clear();
        PrteApplication.facebookUserList.clear();
        for (User user : intermediateList) {
            Log.d("MatchEvent", "Match on Event & Withings: " + user.toString());
        }

        if (intermediateList.isEmpty()) {
            Toast.makeText(this, "No one with Withings went to the Event :-(", 1).show();
            return;
        }

        Toast.makeText(this, "Withings party goers found!", 0).show();

        Intent intent = new Intent(JoinUsersActivity.this, GetWithingsMovementActivity.class);
        startActivity(intent);
        finish();
    }
}
