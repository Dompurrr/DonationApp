package com.dompurrr.kursachpe.repositories;

import com.dompurrr.kursachpe.entities.Streamer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StreamerRepoImpl implements StreamerRepo {
    ArrayList<Streamer> db;

    public StreamerRepoImpl(){
        db = new ArrayList<>(){{
            add(new Streamer(1L, "maxim_login", "Макс, стример по майнкрафту", "https://vk.ru/"));
            add(new Streamer(12L, "alesha_login","Леха, 10 лет, тиктокер", "https://twitch.com/", "Hello, i'm Lexa!!!"));
            add(new Streamer(132L, "denis_login","Денис", "https://youtube.com/"));
        }};
    }

    @Override
    public Streamer find(Long id) {
        List<Streamer> res = db.stream().filter( x->x.getId().equals(id) ).collect(Collectors.toList());
        if (res.isEmpty())
            return null;
        else
            return res.get(0);
    }

    public Streamer findByLogin(String login){
        List<Streamer> res = db.stream().filter(x->x.getLogin().equals(login)).collect(Collectors.toList());
        if (res.isEmpty())
            return null;
        else
            return res.get(0);
    }

    @Override
    public List<Streamer> getAll() {
        List<Streamer> res = db.stream().collect(Collectors.toList());
        return res;
    }

    @Override
    public Boolean delete(Long id) {
        if (find(id) != null) {
            db.removeIf(x -> x.getId().equals(id));
            return true;
        }
        else
            return false;
    }

    @Override
    public Streamer save(Streamer streamer) {
        db.add(streamer);
        return streamer;
    }
}
