package fr.bruno.mamediatheque.bo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

    private String title;
    private int nbPages;
    private int releaseYear;
    private String synopsis;

    @Column(columnDefinition="text")
    private String bookCover;

    //    Pour récupérer la liste des auteurs
    @ManyToMany
    private List<Writer> writer;

}
