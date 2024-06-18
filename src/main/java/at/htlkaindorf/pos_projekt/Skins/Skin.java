package at.htlkaindorf.pos_projekt.Skins;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "skins")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String champName;

    @Column(nullable = false)
    Integer skinID;

    @Column(nullable = false)
    String skinName;

    @Column(nullable = false)
    boolean chromas;

    @Column(nullable = false)
    Double price;
}
