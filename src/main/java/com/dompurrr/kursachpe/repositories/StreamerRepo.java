package com.dompurrr.kursachpe.repositories;

import com.dompurrr.kursachpe.entities.Streamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamerRepo extends JpaRepository<Streamer, Long> {
    Streamer findByStreamerId(Long id);

    Streamer findByLogin(String login);

    Streamer findByName(String name);

    List<Streamer> findAll();

    void deleteById(Long id);
}
