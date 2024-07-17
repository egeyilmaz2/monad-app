package com.example.demo;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@CssImport(value = "./themes/my-theme/styles.css")
public class MainView extends Div {
    public MainView() {
        setClassName("main-view");

        Image logo = new Image("images/logo.png", "Logo");
        logo.setClassName("main-logo");

        Span welcomeText = new Span("Merhaba, Vaadin ile yazılmış frontend projesine hoşgeldiniz. Lütfen sol taraftaki menü üzerinden yapacağınız işlemi seçiniz.");
        welcomeText.setClassName("welcome-text");

        Div content = new Div(logo, welcomeText);
        content.addClassName("content");
        add(content);
    }
}