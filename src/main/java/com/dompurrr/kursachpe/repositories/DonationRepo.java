package com.dompurrr.kursachpe.repositories;

import com.dompurrr.kursachpe.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<Donation, Long> {
    Donation findByDonationId(Long id);

    List<Donation> findByRecipientId(Long recipientId);

    List<Donation> findAll();
}
