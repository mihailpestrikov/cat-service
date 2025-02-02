package ru.pestrikov.catservice.out.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.domain.out.infrastructure.OwnerRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OwnerJpaAdapter implements OwnerRepository {
    private final OwnerJpaRepository ownerJpaRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerJpaRepository.save(owner);
    }

    @Override
    public void delete(UUID id) {
        ownerJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Owner> findById(UUID id) {
        return ownerJpaRepository.findById(id);
    }
}
