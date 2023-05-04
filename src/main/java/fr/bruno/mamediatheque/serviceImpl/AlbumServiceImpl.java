package fr.bruno.mamediatheque.serviceImpl;

import fr.bruno.mamediatheque.bo.Album;
import fr.bruno.mamediatheque.dao.AlbumRepository;
import fr.bruno.mamediatheque.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void addAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public List<Album> listAlbum() {
        return albumRepository.findAll();
    }

    @Override
    public Album getAlbum(int id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAlbum(int id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Album getSingleAlbum(int idAlbum) {
        return albumRepository.findById(idAlbum).get();
    }
}
