package fr.bruno.mamediatheque.service;

import fr.bruno.mamediatheque.bo.Book;

import java.util.List;

public interface BookService {

    //Ajouter un livre
    public void addBook(Book book);

    //Récupérer une liste de livre
    public List<Book> listBook();

    // Récupérer un livre en fonction de son id
    public Book getBook(int id);

    // Supprimer un livre
    public void deleteBook(int id);

    public Book getSingleBook(int book);
}
