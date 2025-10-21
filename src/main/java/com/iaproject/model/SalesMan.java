package com.iaproject.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SalesMan {
    private String firstname;
    private String lastname;
    private Integer sid;
    private List<SocialPerformanceRecord> socialPerformanceRecords;

    public SalesMan(String firstname, String lastname, Integer sid, List<SocialPerformanceRecord> performanceRecords) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sid = sid;
        this.socialPerformanceRecords = performanceRecords;
    }

    public void addPerformanceRecord(SocialPerformanceRecord record) {
        socialPerformanceRecords.add(record);
    }

    public List<SocialPerformanceRecord> getSocialPerformanceRecords() {
        return socialPerformanceRecords;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return sid;
    }

    public void setId(Integer sid) {
        this.sid = sid;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("firstname" , this.firstname );
        document.append("lastname" , this.lastname );
        document.append("sid" , this.sid);
        return document;
    }
}