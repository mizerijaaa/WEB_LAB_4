package mk.ukim.finki.wp.lab.service.implementation;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album findById(long id) {
        return albumRepository.findAlbumById(id);
    }


}
