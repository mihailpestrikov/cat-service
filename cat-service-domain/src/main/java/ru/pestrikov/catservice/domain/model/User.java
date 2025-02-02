package ru.pestrikov.catservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "users_owners_id_fk"))
    private Owner owner;

    public User update(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();

        return this;
    }

}
