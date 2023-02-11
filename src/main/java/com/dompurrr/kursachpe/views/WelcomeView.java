package com.dompurrr.kursachpe.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value="")
@AnonymousAllowed
// Might be a problem - external image url, but local storing has some problems too
@CssImport("./styles/welcome-styles.css")
public class WelcomeView extends Div {
    public WelcomeView(){
        setSizeFull();

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSizeFull();

        Button leftButton = new Button("Я стример!", event -> UI.getCurrent().navigate("influencer"));
        leftButton.addClassName("left-button");
        buttonsLayout.add(leftButton);

        Button rightButton = new Button("Я зритель!", event -> UI.getCurrent().navigate("donate"));
        rightButton.addClassName("right-button");
        buttonsLayout.add(rightButton);

        add(buttonsLayout);
    }

}
