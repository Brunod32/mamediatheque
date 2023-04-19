package fr.bruno.mamediatheque.dao;

import fr.bruno.mamediatheque.bo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book , Integer> {
}
