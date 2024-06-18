package at.htlkaindorf.pos_projekt.user;

import at.htlkaindorf.pos_projekt.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    String firstName;

    String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;
}