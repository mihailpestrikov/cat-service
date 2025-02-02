package ru.pestrikov.catservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "owners")
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (name = "name")
    private String name;

    @Column (name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Cat> cats;

    public Owner update(Owner owner) {
        this.name = owner.getName();
        this.birthDate = owner.getBirthDate();

        return this;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
