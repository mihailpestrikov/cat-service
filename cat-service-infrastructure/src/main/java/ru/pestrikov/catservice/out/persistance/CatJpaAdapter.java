package ru.pestrikov.catservice.out.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pestrikov.catservice.domain.model.Cat;
import ru.pestrikov.catservice.domain.out.infrastructure.CatRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CatJpaAdapter implements CatRepository {
    private final CatJpaRepository catJpaRepository;
    @Override
    public Cat save(Cat cat) {
        return catJpaRepository.save(cat);
    }

    @Override
    public void delete(UUID id) {
        catJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Cat> findById(UUID id) {
        return catJpaRepository.findById(id);
    }

    @Override
    public Optional<Cat> findByName(String name) {
        return catJpaRepository.findByName(name);
    }

    @Override
    public List<Cat> GetStrayCats() {
        return catJpaRepository.findByOwnerIsNull();
    }

    @Override
    public List<Cat> getAllCats() {
        return catJpaRepository.findAll();
    }
}
