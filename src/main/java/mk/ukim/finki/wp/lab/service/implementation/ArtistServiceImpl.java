package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final InMemoryArtistRepository artistRepository;

    public ArtistServiceImpl(InMemoryArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {

        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {

        return artistRepository.findById(id);
    }
}
