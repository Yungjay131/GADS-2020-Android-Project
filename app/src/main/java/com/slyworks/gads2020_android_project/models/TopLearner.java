package com.slyworks.gads2020_android_project.models;


public class TopLearner {

    private String name;

    private int hours;

    private String country;

    private String badgeUrl;

    public TopLearner(String name, String country, int hours, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.hours = hours;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHours() {
        return hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}