package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryArtistRepository {

    public List<Artist> findAll(){

        return DataHolder.artists;
    }

    public Artist findById(Long id){
        return DataHolder.artists.stream()
                .filter(item->item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
