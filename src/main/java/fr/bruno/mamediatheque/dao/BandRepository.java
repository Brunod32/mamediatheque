package fr.bruno.mamediatheque.dao;

import fr.bruno.mamediatheque.bo.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band, Integer> {
}
