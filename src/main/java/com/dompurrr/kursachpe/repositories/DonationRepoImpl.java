package com.dompurrr.kursachpe.repositories;

import com.dompurrr.kursachpe.entities.Donation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationRepoImpl implements DonationRepo {
    ArrayList<Donation> db;

    public DonationRepoImpl(){
        db = new ArrayList<>(){{
            add(new Donation(1,12L, 100, "You're bad streamer!!!", "Max"));
            add(new Donation(2,12L, 13, "Maxim"));
            add(new Donation(3,12L, 999, "Maxim"));
            add(new Donation(4,132L, 100, "Have a nice day!","Tom"));
            add(new Donation(5,132L, 123, "Max"));
            add(new Donation(6,132L, 14100, "Pipipupupu","Max"));
            add(new Donation(7,132L, 13400, "Max"));
            add(new Donation(8,1L, 13034, "Max"));
        }};
    }

    @Override
    public List<Donation> find(Long id) {
        List<Donation> res = db.stream().filter( x->x.getRecipientId().equals(id) ).collect(Collectors.toList());
        if (res.isEmpty())
            return null;
        else
            return res;
    }

    @Override
    public List<Donation> getAll() {
        List<Donation> res = db.stream().collect(Collectors.toList());
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
    public Donation save(Donation donation) {
        int l = db.size();
        donation.setId(db.get(l-1).getId()+1);
        db.add(donation);
        return donation;
    }
}
