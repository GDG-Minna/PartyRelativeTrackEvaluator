package com.blundell.prte.stuff;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Event")
public class Event extends ParseObject {
    public Event() {
    }

    public String getName() {
        return getString("name");
    }

    public Event setName(String name) {
        put("name", name);
        return this;
    }

    public int getId() {
        return getInt("id");
    }

    public Event setId(int id) {
        put("id", id);
        return this;
    }

    public String getStartTime() {
        return getString("startTime");
    }

    public Event setStartTime(String startTime) {
        put("startTime", startTime);
        return this;
    }

    public String getEndTime() {
        return getString("endTime");
    }

    public Event setEndTime(String endTime) {
        put("endTime", endTime);
        return this;
    }

    @Override
    public String toString() {
        return getName();
    }
}
