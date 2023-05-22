package fr.bruno.mamediatheque.api;

import fr.bruno.mamediatheque.bo.Album;
import fr.bruno.mamediatheque.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/album")
public class AlbumRestController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<Album> getAlbums() {
        return albumService.listAlbum();
    }

    @GetMapping("/{idAlbum}")
    private Album getSingleAlbum(@PathVariable Integer idAlbum) {
        return albumService.getSingleAlbum(idAlbum);
    }

    @PostMapping
    public Album album(@RequestBody Album album) {
        albumService.addAlbum(album);
        return album;
    }

    @DeleteMapping("/{idAlbumToDelete}")
    public void deleteAlbum(@PathVariable Integer idAlbumToDelete) {
        if (idAlbumToDelete != null) {
            albumService.deleteAlbum(idAlbumToDelete);
        }
    }

    @PutMapping("/{idAlbumToUpdate}")
    public Album updateAlbum(@PathVariable int idAlbumToUpdate, @RequestBody Album albumToUpdate) {
        return albumService.updateAlbum(idAlbumToUpdate, albumToUpdate);
    }
}
