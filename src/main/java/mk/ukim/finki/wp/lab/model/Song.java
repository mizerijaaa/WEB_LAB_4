package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Song {
    String trackId;

    String title;

    String genre;

    int releaseYear;

    @ManyToMany
    List<Artist> performers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
//        this.id = (long) (Math.random()*100);
        this.album = album;
    }

    public Song() {}
}
