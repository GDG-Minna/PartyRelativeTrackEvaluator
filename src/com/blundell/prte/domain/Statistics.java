package com.blundell.prte.domain;

public class Statistics {
    long distanceCovered;
    int caloriesBurned;
    double intensity;

    public long getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(long distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
