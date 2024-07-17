package com.example.demo;

import com.example.demo.models.User;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class UserCard extends Div {

    public UserCard(User user) {
        HorizontalLayout layout = new HorizontalLayout();

        TextField firstNameField = new TextField();
        firstNameField.setLabel("Ad");
        firstNameField.setValue(user.getFirstName());
        firstNameField.setReadOnly(true);

        TextField lastNameField = new TextField();
        lastNameField.setLabel("Soyad");
        lastNameField.setValue(user.getLastName());
        lastNameField.setReadOnly(true);

        TextField tcNumberField = new TextField();
        tcNumberField.setLabel("TC Kimlik Numarası");
        tcNumberField.setValue(user.getTcNumber());
        tcNumberField.setReadOnly(true);

        layout.add(firstNameField, lastNameField, tcNumberField);
        add(layout);
    }
}