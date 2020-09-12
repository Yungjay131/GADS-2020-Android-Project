package com.slyworks.gads2020_android_project.network;

public class Submit {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String projectLink;

    public Submit(String firstName, String lastName, String emailAddress, String projectLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.projectLink = projectLink;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }
}
