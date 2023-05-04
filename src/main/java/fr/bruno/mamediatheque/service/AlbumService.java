package fr.bruno.mamediatheque.service;

import fr.bruno.mamediatheque.bo.Album;

import java.util.List;

public interface AlbumService {

    public void addAlbum(Album album);

    public List<Album> listAlbum();

    public Album getAlbum(int id);

    public void deleteAlbum(int id);

    public Album getSingleAlbum(int idAlbum);
}
