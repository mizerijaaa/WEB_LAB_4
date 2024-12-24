package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String genre;

    private String releaseYear;

    @OneToMany(mappedBy = "album")
    private List<Song> songList;

    public Album(String name, String genre, String releaseYear) {
//        this.id = (long) (Math.random()*100);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songList = new ArrayList<>();
    }

    public Album() {}
}
