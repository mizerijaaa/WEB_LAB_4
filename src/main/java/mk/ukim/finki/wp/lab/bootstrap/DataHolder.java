package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Album> albums;
    public static List<Song> songs;
    public static List<Artist> artists;

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public DataHolder(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
    public void init() {
        songRepository.deleteAll();
        albumRepository.deleteAll();

        albums = new ArrayList<>();
        if (albumRepository.count() == 0) {
            albums.add(new Album("R&B Album", "R&B", "2008"));
            albums.add(new Album("22 Album", "Pop", "2022"));
            albums.add(new Album("Rap Album", "Rap", "2001"));
            albums.add(new Album("Classical Album", "Classical", "2017"));
            albums.add(new Album("Rock Album", "Rock", "2003"));

            albumRepository.saveAll(albums);
        } else {
            albums = albumRepository.findAll();
        }

        songs = songRepository.findAll();
        if (songs.isEmpty()) {
            songs.add(new Song("1", "Nutcracker", "Classical", 2011, findAlbumByName("Classical Album")));
            songs.add(new Song("2", "Billie Jean", "Pop", 1982, findAlbumByName("22 Album")));
            songs.add(new Song("3", "Thinkin Bout You", "R&B", 2012, findAlbumByName("R&B Album")));
            songs.add(new Song("4", "Bohemian Rhapsody", "Rock", 1975, findAlbumByName("Rock Album")));
            songs.add(new Song("5", "SICKO MODE", "Hip-Hop", 2018, findAlbumByName("Rap Album")));

            songRepository.saveAll(songs);
        }

        artists = new ArrayList<>();
        artists.add(new Artist("Adele", "Adkins", "A globally renowned singer-songwriter with a soulful voice, known for her chart-topping hits and deeply emotional ballads."));
        artists.add(new Artist("Michael", "Jackson", "The 'King of Pop,' an iconic performer and music innovator celebrated for his groundbreaking albums and legendary dance moves."));
        artists.add(new Artist("Frank", "Ocean", "A critically acclaimed R&B artist and songwriter, recognized for his introspective lyrics and genre-defying music."));
        artists.add(new Artist("Freddie", "Mercury", "The legendary frontman of Queen, famed for his electrifying stage presence, powerful vocals, and timeless rock anthems."));
        artists.add(new Artist("Travis", "Scott", "A trailblazing rapper and producer, known for his genre-bending sound, high-energy performances, and cultural influence."));
    }


    private Album findAlbumByName(String albumName) {
        return albums.stream()
                .filter(album -> album.getName().equals(albumName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Album not found: " + albumName));
    }
}
