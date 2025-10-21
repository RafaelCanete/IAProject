package com.iaproject.model;

public class SocialPerformanceRecord {
    private String skill;
    private int socialScore;

    public SocialPerformanceRecord(String skill, int socialScore) {
        this.skill = skill;
        this.socialScore = socialScore;
    }

    public String getSkill() { return skill; }
    public int getSocialScore() { return socialScore; }
}
