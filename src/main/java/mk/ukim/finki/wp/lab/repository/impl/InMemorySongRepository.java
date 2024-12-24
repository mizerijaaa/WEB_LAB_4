package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySongRepository {

    public List<Song> findAll() {

        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId)
    {
        return DataHolder.songs.stream()
                .filter(item->item.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        song.getPerformers().add(artist);
        return artist;
    }

    public void saveSong(String title, String trackId, String genre, int releaseYear, Album album) {
        DataHolder.songs.add(new Song(title, trackId, genre, releaseYear, album));
    }
    public Song findById(long id){
        return DataHolder.songs.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteSongById(long id) {
        DataHolder.songs.removeIf(song -> song.getId().equals(id));
    }

}
