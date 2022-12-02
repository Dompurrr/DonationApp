package com.dompurrr.kursachpe.views;

import com.dompurrr.kursachpe.entities.Donation;
import com.dompurrr.kursachpe.entities.Streamer;
import com.dompurrr.kursachpe.repositories.DonationRepo;
import com.dompurrr.kursachpe.repositories.StreamerRepo;
import com.dompurrr.kursachpe.security.SecurityService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@PageTitle("Список пожертвований")
@Route(value = "influencer/donationlist", layout = InfluencerView.class)
@RolesAllowed("USER")
public class DonationListView extends VerticalLayout {
    Grid<Donation> grid;
    DonationRepo donationRepo;
    SecurityService securityService;
    StreamerRepo streamerRepo;

    @Autowired
    public DonationListView(DonationRepo donationRepo, SecurityService securityService, StreamerRepo streamerRepo){
        this.donationRepo = donationRepo;
        this.securityService = securityService;
        this.streamerRepo = streamerRepo;
        constructPage();
    }

    private void constructPage(){
        // Grid
        grid = new Grid<>();
        grid.addColumn(Donation::getSenderName).setHeader("Отправитель");
        grid.addColumn(Donation::getAmount).setHeader("Сумма");
        grid.addColumn(Donation::getTextMessage).setHeader("Сообщение");
        String usrnm = securityService.getUsername();
        Streamer tmp = streamerRepo.findByLogin(usrnm);
        List<Donation> donationList = donationRepo.find(tmp.getId());
        grid.setItems(donationList);
        add(grid);
        // EditForm
        Div staticsBlock = new Div();
        H4 stN = new H4("Количество транзакций: " + donationList.size());
        Integer totalS = donationList.stream().mapToInt(Donation::getAmount).sum();
        H4 stTotal = new H4("Общая сумма: " + totalS);
        staticsBlock.add(stN);
        staticsBlock.add(stTotal);
        add(staticsBlock);
    }
}
