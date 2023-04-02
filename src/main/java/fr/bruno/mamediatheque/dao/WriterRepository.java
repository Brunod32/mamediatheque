package fr.bruno.mamediatheque.dao;

import fr.bruno.mamediatheque.bo.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Integer> {
}
