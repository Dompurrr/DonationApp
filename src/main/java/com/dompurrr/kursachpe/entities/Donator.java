package com.dompurrr.kursachpe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Donator {
    Long id;
    Boolean hasAccount;
    String name;
    Long auth_id;

    public Donator(Long id, Boolean hasAccount, String name) {
        this.id = id;
        this.hasAccount = hasAccount;
        this.hasAccount = false;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Donator{" +
                "id=" + id +
                ", hasAccount=" + hasAccount +
                ", name='" + name + '\'' +
                ", auth_id=" + auth_id +
                '}';
    }
}
