package ru.pestrikov.catservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import ru.pestrikov.catservice.domain.model.resource.CatColor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cats")
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (name = "name")
    private String name;

    @Column (name = "birth_date")
    private LocalDate birthDate;

    @Column (name = "color")
    @Enumerated(EnumType.STRING)
    private CatColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Cat> friends;

    public Cat update(Cat cat) {
        this.name = cat.getName();
        this.color = cat.getColor();
        this.owner = cat.getOwner();
        this.birthDate = cat.getBirthDate();

        return this;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
