package com.dompurrr.kursachpe.views;


import com.dompurrr.kursachpe.entities.Donation;
import com.dompurrr.kursachpe.entities.Donator;
import com.dompurrr.kursachpe.security.SecurityService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@PageTitle("Аналитика")
@Route(value = "/influencer/analytics", layout = InfluencerView.class)
@RolesAllowed("USER")
public class AnalyticsView extends VerticalLayout {

    private SecurityService securityService;

    @Autowired
    public AnalyticsView(SecurityService securityService){
        this.securityService = securityService;

        H1 topDonation = new H1(getTopDonation());
        H1 topDonator = new H1(getTopDonator());
        H1 monthlyIncome = new H1(getMonthIncome());
        add(topDonation);
        add(topDonator);
        add(monthlyIncome);
    }

    private String getTopDonation(){
        Donation res = new Donation(1, 123L, 10000, "It's the biggest donation", "topDonator");
        return res.toString();
    }

    private  String getTopDonator(){
        Donator res = new Donator(1L, false, "Maxim");
        return res.toString();
    }

    private String getMonthIncome(){
        return "1000 руб.";
    }
}
