package fr.bruno.mamediatheque.serviceImpl;

import fr.bruno.mamediatheque.bo.Book;
import fr.bruno.mamediatheque.dao.BookRepository;
import fr.bruno.mamediatheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> listBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null); //Retourne null si pas trouvé
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getSingleBook(int idBook) {
        return bookRepository.findById(idBook).get();
    }

}
