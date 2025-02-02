package ru.pestrikov.catservice.domain.out.infrastructure;

import ru.pestrikov.catservice.domain.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends BaseRepository<Cat> {
    Optional<Cat> findByName(String name);
    List<Cat> GetStrayCats();
    List<Cat> getAllCats();
}
