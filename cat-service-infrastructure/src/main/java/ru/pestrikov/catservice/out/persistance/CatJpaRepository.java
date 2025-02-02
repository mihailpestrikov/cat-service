package ru.pestrikov.catservice.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pestrikov.catservice.domain.model.Cat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatJpaRepository extends JpaRepository<Cat, UUID> {
    Optional<Cat> findByName(String name);
    List<Cat> findByOwnerIsNull();
}
