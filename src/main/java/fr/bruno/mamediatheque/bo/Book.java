package fr.bruno.mamediatheque.bo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
//    @ManyToMany
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Writer> writer;

    //    Pour récupérer l'auteur
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Writer writer;


}
