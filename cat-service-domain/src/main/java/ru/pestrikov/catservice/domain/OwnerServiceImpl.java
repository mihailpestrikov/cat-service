package ru.pestrikov.catservice.domain;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ru.pestrikov.catservice.domain.excpetion.OwnershipException;
import ru.pestrikov.catservice.domain.out.infrastructure.CatRepository;
import ru.pestrikov.catservice.domain.out.infrastructure.OwnerRepository;
import ru.pestrikov.catservice.domain.architecture.DomainService;
import ru.pestrikov.catservice.domain.in.application.OwnerService;
import ru.pestrikov.catservice.domain.model.Owner;

import java.util.Optional;
import java.util.UUID;

@DomainService
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Override
    public void makeOwnership(UUID ownerId, UUID catId) {
        var catOptional = catRepository.findById(catId);
        var ownerOptional = ownerRepository.findById(ownerId);

        if (catOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + catId + " not found");
        }

        if (ownerOptional.isEmpty()) {
            throw new EntityNotFoundException("Owner with id " + ownerId + " not found");
        }

        var cat = catOptional.get();
        var owner = ownerOptional.get();
        var ownerCats = owner.getCats();

        if (!ownerCats.contains(cat)) {
            ownerCats.add(cat);
            cat.setOwner(owner);

            ownerRepository.save(owner);
            catRepository.save(cat);
        }
        else {
            throw new OwnershipException("Owner " + ownerId + " already has cat " + catId);
        }
    }

    @Override
    public void removeOwnership(UUID ownerId, UUID catId) {
        var catOptional = catRepository.findById(catId);
        var ownerOptional = ownerRepository.findById(ownerId);

        if (catOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + catId + " not found");
        }

        if (ownerOptional.isEmpty()) {
            throw new EntityNotFoundException("Owner with id " + ownerId + " not found");
        }

        var cat = catOptional.get();
        var owner = ownerOptional.get();
        var ownerCats = owner.getCats();

        if (ownerCats.contains(cat)) {
            ownerCats.remove(cat);
            cat.setOwner(null);

            ownerRepository.save(owner);
            catRepository.save(cat);
        }
        else {
            throw new OwnershipException("Owner " + ownerId + " does not have cat " + catId);
        }
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(UUID id, Owner object) {
        var ownerOptional = ownerRepository.findById(id);

        if (ownerOptional.isEmpty()) {
            throw new EntityNotFoundException("Owner with id " + id + " not found");
        }

        var owner = ownerOptional.get();

        return ownerRepository.save(owner.update(object));
    }

    @Override
    public Owner deletedById(UUID id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isPresent()) {
            ownerRepository.delete(id);
            return ownerOptional.get();
        } else {
            throw new EntityNotFoundException("Owner with id " + id + " not found");
        }
    }

    @Override
    public Owner findById(UUID id) {
        var ownerOptional = ownerRepository.findById(id);

        if (ownerOptional.isEmpty()) {
            throw new EntityNotFoundException("Owner with id " + id + " not found");
        }

        return ownerOptional.get();
    }
}
