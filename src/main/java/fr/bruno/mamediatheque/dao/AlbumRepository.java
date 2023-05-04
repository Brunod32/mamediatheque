package fr.bruno.mamediatheque.dao;

import fr.bruno.mamediatheque.bo.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
