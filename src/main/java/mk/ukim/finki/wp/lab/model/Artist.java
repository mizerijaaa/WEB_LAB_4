package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String lastName;

    String bio;

    public Artist(String firstName, String lastName, String bio) {
//        this.id = (long)(Math.random()*100);
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public Artist() {}
}
