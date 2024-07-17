package com.example.demo;

import com.example.demo.models.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "add-user", layout = MainLayout.class)
@CssImport(value = "./themes/my-theme/styles.css")
@Component
@UIScope
public class AddUserView extends VerticalLayout {

    @Value("${backend.url}")
    private String backendUrl;

    private List<User> users = new ArrayList<>();
    private Div userListLayout = new Div();
    private TextField firstNameField = new TextField("Ad");
    private TextField lastNameField = new TextField("Soyad");
    private TextField tcNumberField = new TextField("TC Kimlik Numarası");
    private Button addButton = new Button("Ekle");
    private User editingUser = null;

    public AddUserView() {
        setPadding(true);
        setSpacing(true);
        setWidthFull();

        FormLayout formLayout = new FormLayout();

        firstNameField.setWidthFull();
        lastNameField.setWidthFull();
        tcNumberField.setWidthFull();

        formLayout.add(firstNameField, lastNameField, tcNumberField, addButton);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2)
        );
        formLayout.setColspan(addButton, 2);

        addButton.addClickListener(e -> {
            String firstName = firstNameField.getValue();
            String lastName = lastNameField.getValue();
            String tcNumber = tcNumberField.getValue();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !tcNumber.isEmpty()) {
                if (editingUser == null) {
                    User user = new User(firstName, lastName, tcNumber);
                    users.add(user);
                    try {
                        saveUserToBackend(user);
                        Notification.show("Kullanıcı eklendi!");
                    } catch (Exception ex) {
                        Notification.show("Kullanıcı yerel olarak eklendi. Backend'e kaydedilemedi.");
                    }
                } else {
                    editingUser.setFirstName(firstName);
                    editingUser.setLastName(lastName);
                    editingUser.setTcNumber(tcNumber);
                    try {
                        saveUserToBackend(editingUser);
                        Notification.show("Kullanıcı güncellendi!");
                    } catch (Exception ex) {
                        Notification.show("Kullanıcı yerel olarak güncellendi. Backend'e kaydedilemedi.");
                    }
                    editingUser = null;
                    addButton.setText("Ekle");
                }

                displayUsers();
                firstNameField.clear();
                lastNameField.clear();
                tcNumberField.clear();
            } else {
                Notification.show("Lütfen tüm alanları doldurun.");
            }
        });

        userListLayout.addClassName("user-list-layout");

        add(formLayout, userListLayout);

        loadUsersFromBackend();
        displayUsers();
    }

    private void displayUsers() {
        userListLayout.removeAll();
        for (User user : users) {
            userListLayout.add(createUserCard(user));
        }
    }

    private Div createUserCard(User user) {
        Div card = new Div();
        card.addClassName("user-card");

        TextField firstName = new TextField("Ad");
        firstName.setValue(user.getFirstName());
        TextField lastName = new TextField("Soyad");
        lastName.setValue(user.getLastName());
        TextField tcNumber = new TextField("TC Kimlik Numarası");
        tcNumber.setValue(user.getTcNumber());

        firstName.setReadOnly(true);
        lastName.setReadOnly(true);
        tcNumber.setReadOnly(true);

        Button editButton = new Button("Düzenle");
        editButton.addClickListener(e -> {
            firstNameField.setValue(user.getFirstName());
            lastNameField.setValue(user.getLastName());
            tcNumberField.setValue(user.getTcNumber());
            editingUser = user; 
            addButton.setText("Düzenlemeyi Bitir");
        });

        Button deleteButton = new Button("Sil");
        deleteButton.addClickListener(e -> {
            users.remove(user);
            try {
                deleteUserFromBackend(user);
                Notification.show("Kullanıcı silindi!");
            } catch (Exception ex) {
                Notification.show("Kullanıcı yerel olarak silindi. Backend'den silinemedi.");
            }
            displayUsers();
        });

        HorizontalLayout buttons = new HorizontalLayout(editButton, deleteButton);
        buttons.addClassName("horizontal-buttons");
        card.add(firstName, lastName, tcNumber, buttons);
        return card;
    }

    private void saveUserToBackend(User user) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(backendUrl, user, User.class);
    }

    private void deleteUserFromBackend(User user) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendUrl + "/" + user.getTcNumber());
    }

    private void loadUsersFromBackend() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            User[] usersArray = restTemplate.getForObject(backendUrl, User[].class);
            if (usersArray != null) {
                users.addAll(Arrays.asList(usersArray));
            }
        } catch (Exception ex) {
            Notification.show("Backend'den kullanıcılar yüklenemedi.");
        }
    }
}