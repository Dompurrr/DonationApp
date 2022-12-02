package com.dompurrr.kursachpe.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;


@PageTitle("Аналитика")
@Route(value = "/influencer/pay", layout = InfluencerView.class)
@RolesAllowed("USER")
public class PayView extends VerticalLayout {
    private int counter = 0;

    public PayView() {
        Button button = new Button("I want money!");
        add(button);
    }
}
