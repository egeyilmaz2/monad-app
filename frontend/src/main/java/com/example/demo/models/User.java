package com.example.demo.models;

import com.vaadin.flow.component.html.Div;

public class User {
    private String firstName;
    private String lastName;
    private String tcNumber;

    // Constructors, getters, and setters
    public User(String ad, String soyAd, String tcNo) {
        this.firstName = ad;
        this.lastName = soyAd;
        this.tcNumber = tcNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTcNumber() {
        return tcNumber;
    }

    public void setTcNumber(String tcNumber) {
        this.tcNumber = tcNumber;
    }

    public Div createCard() {
        Div card = new Div();
        card.setText("Ad: " + firstName + ", SoyAd: " + lastName + ", Tc No: " + tcNumber);
        return card;
    }
}
