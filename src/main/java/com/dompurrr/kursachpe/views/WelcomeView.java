package com.dompurrr.kursachpe.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value="")
@AnonymousAllowed
public class WelcomeView extends VerticalLayout {

    public WelcomeView(){
        Button influencerButton = new Button("Я контентмейкер!", event -> UI.getCurrent().navigate("influencer"));
        Button donatorButton = new Button("Я зритель!", event -> UI.getCurrent().navigate("donate"));

        addClassName("welcome-view");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        add(influencerButton, donatorButton);

    }

}
