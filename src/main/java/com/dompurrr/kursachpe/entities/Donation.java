package com.dompurrr.kursachpe.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "donationId")
@NoArgsConstructor
@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donationId;
    @Column(name = "recipient_id")
    private Long recipientId;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "message")
    private String textMessage;
    @Column(name = "sender_name")
    private String senderName;
}
