package com.dompurrr.kursachpe.views;


import com.dompurrr.kursachpe.entities.Donation;
import com.dompurrr.kursachpe.entities.Streamer;
import com.dompurrr.kursachpe.repositories.DonationRepo;
import com.dompurrr.kursachpe.repositories.StreamerRepo;
import com.dompurrr.kursachpe.security.SecurityService;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@PageTitle("Аналитика")
@Route(value = "/influencer/analytics", layout = InfluencerView.class)
@RolesAllowed("USER")
public class AnalyticsView extends VerticalLayout {

    private SecurityService securityService;

    private final DonationRepo donationRepo;
    private final StreamerRepo streamerRepo;

    @Autowired
    public AnalyticsView(SecurityService securityService, DonationRepo donationRepo, StreamerRepo streamerRepo){
        this.securityService = securityService;
        this.donationRepo = donationRepo;
        this.streamerRepo = streamerRepo;
        Streamer streamer = streamerRepo.findByLogin(securityService.getUsername());

        Paragraph topDonation = new Paragraph(getTopDonation(streamer));
        Paragraph topDonator = new Paragraph(getTopDonator(streamer));
        add(topDonation);
        add(topDonator);
    }

    private String getTopDonation(Streamer streamer){
        var donations = donationRepo.findByRecipientId(streamer.getStreamerId());
        if (donations.size() == 0)
            return "У вас нет пожертвований";
        else
            return ("Максимальный донат: " + donations.stream().max(Comparator.comparingDouble(Donation::getAmount)).get().getAmount());
    }

    private  String getTopDonator(Streamer streamer){
        var donations = donationRepo.findByRecipientId(streamer.getStreamerId());
        if (donations.size() == 0)
            return "У вас нет пожертвований";
        else
        {
            Map<String, Double> donorTotals = new HashMap<>();
            for (Donation donation : donations) {
                String donorName = donation.getSenderName();
                double donationAmount = donation.getAmount();
                donorTotals.merge(donorName, donationAmount, Double::sum);
            }
            String maxDonorName = null;
            double maxDonationAmount = Double.MIN_VALUE;
            for (Map.Entry<String, Double> entry : donorTotals.entrySet()) {
                if (entry.getValue() > maxDonationAmount) {
                    maxDonorName = entry.getKey();
                    maxDonationAmount = entry.getValue();
                }
            }
            return ("Самый щедрый отправитель: " + maxDonorName);
        }
    }
}
