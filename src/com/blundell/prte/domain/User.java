package com.blundell.prte.domain;

public class User {
    String name;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
