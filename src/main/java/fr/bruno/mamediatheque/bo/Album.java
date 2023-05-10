package fr.bruno.mamediatheque.bo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int releasedYears;

    @Column(columnDefinition="text")
    private String albumCover;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Band band;
}
