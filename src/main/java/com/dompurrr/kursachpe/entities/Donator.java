package com.dompurrr.kursachpe.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "donatorId")
@Entity
@Table(name = "donator")
public class Donator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long donatorId;
    @Column(name = "has_account")
    Boolean hasAccount;
    @Column(name = "name")
    String name;
    @Column(name = "auth_id")
    Long auth_id;
}
