package com.dompurrr.kursachpe.views;

import com.dompurrr.kursachpe.entities.Donation;
import com.dompurrr.kursachpe.repositories.DonationRepo;
import com.dompurrr.kursachpe.repositories.StreamerRepo;
import com.dompurrr.kursachpe.security.SecurityService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RolesAllowed("USER")
@Route("overlay")
public class OverlayView extends Div {
    private SecurityService securityService;
    private StreamerRepo streamerRepo;
    private DonationRepo donationRepo;
    private Long id;
    private UpdateThread updateThread;

    @Autowired
    public OverlayView(SecurityService securityService,
                       DonationRepo donationRepo,
                       StreamerRepo streamerRepo){
        this.securityService = securityService;
        this.donationRepo = donationRepo;
        this.streamerRepo = streamerRepo;
        this.id = streamerRepo.findByLogin(securityService.getUsername()).getStreamerId();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        add(new Span("Waiting for updates"));

        // Start the data feed thread
        updateThread = new OverlayView.UpdateThread(this, donationRepo, id, attachEvent.getUI());
        updateThread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        updateThread.interrupt();
        updateThread = null;
    }

    private static class UpdateThread extends Thread{
        private List<Donation> donations;
        private final OverlayView overlayView;
        private final DonationRepo donationRepo;
        private final Long id;
        private final UI ui;

        public UpdateThread(OverlayView view,
                            DonationRepo donationRepo,
                            Long id,
                            UI ui) {
            this.overlayView = view;
            this.donationRepo = donationRepo;
            this.id = id;
            this.ui = ui;
            donations = donationRepo.findByRecipientId(id);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    List<Donation> tmp = donationRepo.findByRecipientId(id);
                    List<Donation> difference;
                    if (!tmp.equals(donations)) {
                        difference = tmp.stream()
                                .filter(element -> !donations.contains(element))
                                .collect(Collectors.toList());
                        donations = tmp;
                        showDonations(difference);
                    }
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void showDonations(List<Donation> toShow){
            for (Donation elem : toShow){
                VerticalLayout layout = new VerticalLayout();
                H2 sender = new H2(elem.getSenderName());
                H2 amount = new H2(String.valueOf(elem.getAmount()));
                Paragraph msg = new Paragraph(elem.getTextMessage());
                layout.add(sender, amount, msg);
                ui.access(() -> {
                    overlayView.removeAll();
                    overlayView.add(layout);
                    layout.setAlignItems(FlexComponent.Alignment.CENTER);
                });
            }
        }
    }

}
