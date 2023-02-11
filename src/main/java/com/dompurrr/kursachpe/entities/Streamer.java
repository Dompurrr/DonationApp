package com.dompurrr.kursachpe.entities;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "streamerId")
@Entity
@Table(name = "streamer")
public class Streamer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamerId;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;
    @Column(name = "img_link")
    private String imgLink;
    @Column(name = "description")
    private String description;
}
