package com.dompurrr.kursachpe.views;

import com.dompurrr.kursachpe.repositories.StreamerRepo;
import com.dompurrr.kursachpe.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.*;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@Route("influencer")
@RolesAllowed("USER")
public class InfluencerView extends AppLayout {

    private StreamerRepo streamerRepo;
    private  SecurityService securityService;

    @Autowired
    public InfluencerView(SecurityService securityService, StreamerRepo streamerRepo){
        this.securityService = securityService;
        this.streamerRepo = streamerRepo;
        constructPage();
    }

    private void constructPage(){
        DrawerToggle toggle = new DrawerToggle();
        String name = streamerRepo.findByLogin(securityService.getUsername()).getName();
        H2 title = new H2("Приветствуем, "+name+"!");

        title.addClassName("logo");
        if (securityService.getAuthenticatedUser() != null) {
            Button logout = new Button("Выйти", click ->
                    securityService.logout());
            logout.getStyle().set("position", "relative")
                    .set("right", "0");
            addToNavbar(toggle, title, logout);
        } else {
            addToNavbar(toggle, title);
        }

        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "auto");

        Tabs tabs = getTabs();
        addToDrawer(tabs);
    }

    // Создание вкладок
    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.LIST, "Пожертвования"),
                createTab(VaadinIcon.PIE_BAR_CHART, "Аналитика"),
                createTab(VaadinIcon.INFO, "Информация"),
                createTab(VaadinIcon.COG, "Настройки"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }
    // Создание одной вкладки
    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        if (viewName.equals("Пожертвования"))
            link.setRoute(DonationListView.class);
        if (viewName.equals("Аналитика"))
            link.setRoute(AnalyticsView.class);
        if (viewName.equals("Информация"))
            link.setRoute(InfoView.class);
        if (viewName.equals("Настройки"))
            link.setRoute(SettingsView.class);
        link.setTabIndex(-1);
        return new Tab(link);
    }
}
