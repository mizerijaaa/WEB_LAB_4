package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlbumRepository {
    public List<Album> findAll(){

        return DataHolder.albums;
    }

    public Album findById(long albumId) {
        return DataHolder.albums.stream()
                .filter(album -> album.getId().equals(albumId))
                .findFirst()
                .orElse(null);
    }
}

