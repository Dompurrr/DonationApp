package com.dompurrr.kursachpe.entities;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class Streamer {
    private Long id;
    private String login;
    private String name;
    private String link;
    private String imgLink = "https://standart.link/";
    private String description = "No description";

    public Streamer(Long id, String login, String name, String link) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.link = link;
    }

    public Streamer(Long id, String login, String name, String link, String description) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.link = link;
        this.description = description;
    }
}
