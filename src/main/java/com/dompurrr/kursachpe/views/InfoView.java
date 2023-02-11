package com.dompurrr.kursachpe.views;

import com.dompurrr.kursachpe.entities.Streamer;
import com.dompurrr.kursachpe.repositories.StreamerRepo;
import com.dompurrr.kursachpe.security.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.security.RolesAllowed;



@PageTitle("Аналитика")
@Route(value = "/influencer/info", layout = InfluencerView.class)
@RolesAllowed("USER")
public class InfoView extends VerticalLayout {

    @Value("${site.name}")
    private String siteName;
    private final SecurityService securityService;

    private final StreamerRepo streamerRepo;

    private final Streamer streamer;

    @Autowired
    public InfoView(SecurityService securityService, StreamerRepo streamerRepo) {
        this.securityService = securityService;
        this.streamerRepo = streamerRepo;
        streamer = streamerRepo.findByLogin(securityService.getUsername());
        Paragraph idInfo = new Paragraph("Ваш id: " + streamer.getStreamerId());
        Paragraph nameInfo = new Paragraph("Ваше имя: " + streamer.getName());
        Paragraph linkInfo = new Paragraph("Ваша ссылка: " + streamer.getLink());
        Paragraph loginInfo = new Paragraph("Ваш логин: " + streamer.getLogin());
        Paragraph descInfo = new Paragraph("Описание страницы: " + streamer.getDescription());
        Paragraph imgInfo = new Paragraph("Ссылка на изображение: " + streamer.getImgLink());
        Button overlayLink = new Button("Ссылка на оверлей", event -> UI.getCurrent().navigate("overlay"));
        Paragraph donationLinkInfo = new Paragraph("Прямая ссылка на донат: " + siteName + "/donate/" + idInfo);
        add(idInfo, nameInfo, linkInfo, loginInfo, descInfo, imgInfo, overlayLink);
    }
}
