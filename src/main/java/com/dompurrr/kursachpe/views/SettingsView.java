package com.dompurrr.kursachpe.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@PageTitle("Настройки")
@Route(value = "influencer/settings", layout = InfluencerView.class)
@RolesAllowed("USER")
public class SettingsView extends VerticalLayout {

    public SettingsView(){
        Button button = new Button("Fix everything!");
        add(button);
    }
}
