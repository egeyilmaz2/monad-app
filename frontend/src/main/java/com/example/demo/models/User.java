package com.example.demo.models;

import com.vaadin.flow.component.html.Div;


import java.util.UUID;



public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String tcNumber;

    public User(String firstName, String lastName, String tcNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcNumber = tcNumber;
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
        card.setText(", Ad: " + firstName + ", SoyAd: " + lastName + ", Tc No: " + tcNumber);
        return card;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
