package fr.bruno.mamediatheque.service;

import fr.bruno.mamediatheque.bo.Writer;

import java.util.List;

public interface WriterService {

    // Ajouter un écrivain
    public void addWriter(Writer writer);

    //Récupérer une liste d'écrivains
    public List<Writer> listWriter();

    //Récupérer un écrivain en fonction de son id
    public Writer getWriter(int id);

}
