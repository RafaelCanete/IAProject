package com.iaproject.model;

public class SocialPerformanceRecord {
    private String month;
    private int socialScore;

    public SocialPerformanceRecord(String month, int socialScore) {
        this.month = month;
        this.socialScore = socialScore;
    }

    public String getMonth() { return month; }
    public int getSocialScore() { return socialScore; }
}
