package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final InMemoryAlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, InMemoryAlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {

        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song)
    {
        song.getPerformers().add(artist);
        songRepository.save(song);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {

        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void saveSong(Song song) {

        songRepository.save(song);
    }

    @Override
    public Optional<Song> findSongById(long id) {

        return Optional.ofNullable(songRepository.findSongById(id));
    }

    @Override
    public void deleteSongById(long id) {

        songRepository.deleteById(id);
    }

    List<Song> findAllByAlbumId(Long albumId){
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
