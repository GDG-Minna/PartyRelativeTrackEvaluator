package com.blundell.prte.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {

    public User() {
    }

    public String getName() {
        return getString("name");
    }

    public User setName(String name) {
        put("name", name);
        return this;
    }

    public String getFacebookId() {
        return getString("facebookId");
    }

    public User setFacebookId(String facebookId) {
        put("facebookId", facebookId);
        return this;
    }

    public String getWithingsId() {
        return getString("withingsId");
    }

    public User setWithingsId(String withingsId) {
        put("withingsId", withingsId);
        return this;
    }

    @Override
    public String toString() {
        return getName() + " FID: " + getFacebookId() + " WID: " + getWithingsId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user1 = (User) o;

        return !(getName() != null ? !getName().equals(user1.getName()) : user1.getName() != null);
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
