package com.dompurrr.kursachpe.views;

import com.dompurrr.kursachpe.entities.Donation;
import com.dompurrr.kursachpe.entities.Streamer;
import com.dompurrr.kursachpe.repositories.DonationRepo;
import com.dompurrr.kursachpe.repositories.StreamerRepo;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value="donate")
@AnonymousAllowed
public class DonateView extends Div implements HasUrlParameter<Integer> {
    @Autowired
    private StreamerRepo streamerRepo;
    @Autowired
    private DonationRepo donationRepo;
    private Div container;

    public DonateView(StreamerRepo streamerRepo){
        this.streamerRepo=streamerRepo;
        this.setClassName("donation-app");
        this.setHeight("100%");
        container = new Div();
        container.setMinHeight("100%");
        this.add(container);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        if (parameter == null){
            constructEmpt();
        }
        else {
            constructPage(Long.valueOf(parameter));
        }
    }

    private void constructEmpt(){
        this.removeAll();
        VerticalLayout layout = new VerticalLayout();
        H2 helpLabel = new H2();
        helpLabel.setText("Введите ID ведущего:");
        // Не делать проверку валидности -> в будущем никнейм в виде параметра, а не ID
        TextField inpId = new TextField("Id ведущего");
        inpId.setClearButtonVisible(true);
        Button searchBtn = new Button("Найти");

        searchBtn.addClickListener(e -> {
            String StrId = inpId.getValue();
            Streamer tmp = streamerRepo.findByStreamerId(Long.parseLong(StrId));
            if (tmp == null){
                Notification notification = new Notification();
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("ID не найдено");
                notification.setDuration(5000);
                notification.setPosition(Notification.Position.BOTTOM_STRETCH);
                notification.open();
            }
            else{
                searchBtn.getUI().ifPresent(ui -> ui.navigate("donate/"+StrId));
            }
        });

        layout.add(helpLabel, inpId, searchBtn);
        this.add(layout);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    private void constructPage(Long id){
        VerticalLayout layout = new VerticalLayout();
        H2 h = new H2();

        Streamer foundRes = streamerRepo.findByStreamerId(id);
        this.removeAll();
        if (foundRes == null) {
            h.setText("Неверный ID!");
            layout.add(h);
        }
        else {
            h.setText(String.format("Ведущий: %s!", foundRes.getName()));
            Avatar avatar = new Avatar(foundRes.getName());
            Paragraph p = new Paragraph(foundRes.getDescription());

            TextField donationAmount = new TextField();
            TextField senderName = new TextField();
            senderName.setPlaceholder("Ваше имя");
            senderName.setLabel("Имя");
            donationAmount.setPlaceholder("Сумма");
            donationAmount.setLabel("Сумма");
            TextArea messageText = new TextArea();
            messageText.setLabel("Сообщение");
            messageText.setMaxLength(255);
            messageText.setValueChangeMode(ValueChangeMode.EAGER);
            messageText.addValueChangeListener(e -> {
                e.getSource()
                        .setHelperText(e.getValue().length() + "/" + 256);
            });
            messageText.setValue("");

            Button donateBtn = new Button("Пожертвовать", click -> {
                Integer t = null;
                try {
                    t = Integer.parseInt(donationAmount.getValue());
                }
                catch (Exception e){
                    h.setText("Неверная сумма!");
                    layout.add(h);
                }
                if (t != null) {
                    String msgTxt = messageText.getValue();
                    Donation dnt;
                    if (msgTxt.equals(""))
                        dnt = new Donation(null, id, t, senderName.getValue(), "");
                    else
                        dnt = new Donation(null, id, t, msgTxt, senderName.getValue());
                    donationRepo.save(dnt);
                    h.setText("Платеж отправлен");
                    layout.add(h);
                    donationAmount.clear();
                    senderName.clear();
                    messageText.clear();
                }
            });
            add(messageText);

            HorizontalLayout l2 = new HorizontalLayout(donationAmount, senderName, donateBtn);

            layout.add(h, avatar, p, l2, messageText);
        }

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        this.add(layout);
    }
}
