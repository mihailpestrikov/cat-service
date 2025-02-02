package ru.pestrikov.catservice.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pestrikov.catservice.domain.model.Owner;

import java.util.UUID;

public interface OwnerJpaRepository extends JpaRepository<Owner, UUID> {
}
