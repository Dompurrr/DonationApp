package com.dompurrr.kursachpe.repositories;

import com.dompurrr.kursachpe.entities.Streamer;

import java.util.List;

public interface StreamerRepo {
    public Streamer find(Long id);

    public Streamer findByLogin(String login);

    public List<Streamer> getAll();

    public Boolean delete(Long id);

    public Streamer save(Streamer streamer);
}
