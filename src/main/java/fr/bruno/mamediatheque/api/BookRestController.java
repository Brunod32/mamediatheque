package fr.bruno.mamediatheque.api;

import fr.bruno.mamediatheque.bo.Book;
import fr.bruno.mamediatheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/livre")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.listBook();
    }

    @GetMapping("/{idBook}")
    public Book getSingleBook(@PathVariable Integer idBook) {
        return bookService.getSingleBook(idBook);
    }

    @PostMapping
    public Book book(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @DeleteMapping("/{idBookToDelete}")
    public void deleteBook(@PathVariable Integer idBookToDelete) {
            if (idBookToDelete != null) {
                bookService.deleteBook(idBookToDelete);
            }
    }
}
