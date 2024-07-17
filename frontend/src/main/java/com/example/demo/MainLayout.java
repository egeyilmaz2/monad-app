package com.example.demo;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

@CssImport(value = "./themes/my-theme/styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createDrawer();
    }

    private void createDrawer() {
        H2 title = new H2("User Management");
        title.addClassName("drawer-title");

        RouterLink homeLink = new RouterLink("Home", MainView.class);
        RouterLink addUserLink = new RouterLink("Add User", AddUserView.class);

        VerticalLayout menuLayout = new VerticalLayout(title, homeLink, addUserLink);
        menuLayout.addClassName("menu-layout");

        addToDrawer(menuLayout);
    }
}