package ru.pestrikov.catservice.domain.out.infrastructure;

import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T> {
    T save(T object);
    void delete(UUID id);
    Optional<T> findById(UUID id);
}
