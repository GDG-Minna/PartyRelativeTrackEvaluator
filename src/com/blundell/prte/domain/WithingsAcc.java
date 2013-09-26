package com.blundell.prte.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("WithingsAcc")
public class WithingsAcc extends ParseObject {

    public WithingsAcc() {
    }

    public String getUserId() {
        return getString("userId");
    }

    public WithingsAcc setUserId(String userId) {
        put("userId", userId);
        return this;
    }

    public String getSessionId() {
        return getString("sessionId");
    }

    public WithingsAcc setSessionId(String sessionId) {
        put("sessionId", sessionId);
        return this;
    }

    @Override
    public String toString() {
        return getUserId() + " / " + getSessionId();
    }
}
