package com.dompurrr.kursachpe.repositories;

import com.dompurrr.kursachpe.entities.Donation;

import java.util.List;

public interface DonationRepo {
    public List<Donation> find(Long id);

    public List<Donation> getAll();

    public Boolean delete(Long id);

    public Donation save(Donation donation);
}
