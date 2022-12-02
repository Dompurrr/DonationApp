package com.dompurrr.kursachpe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Donation {
    private Integer id;
    private Long recipientId;
    private Integer amount;
    private String textMessage = "";
    private String senderName;

    public Donation(Integer id, Long recipientId, Integer amount, String senderName) {
        this.id = id;
        this.recipientId = recipientId;
        this.amount = amount;
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id = " + id + ", " +
                "recipientId = " + recipientId + ", " +
                "amount = " + amount + ", " +
                "textMessage = " + textMessage + ", " +
                "senderName = " + senderName + "}";
    }
}
