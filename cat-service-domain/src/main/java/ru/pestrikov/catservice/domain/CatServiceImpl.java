package ru.pestrikov.catservice.domain;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ru.pestrikov.catservice.domain.architecture.DomainService;
import ru.pestrikov.catservice.domain.excpetion.FriendshipException;
import ru.pestrikov.catservice.domain.out.infrastructure.CatRepository;
import ru.pestrikov.catservice.domain.in.application.CatService;
import ru.pestrikov.catservice.domain.model.Cat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DomainService
@AllArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    @Override
    public void makeFriendship(UUID catId, UUID friendId) {
        var catOptional = catRepository.findById(catId);
        var friendOptional = catRepository.findById(friendId);

        if (catOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + catId + " not found");
        }

        if (friendOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + friendId + " not found");
        }

        var cat = catOptional.get();
        var friend = friendOptional.get();
        var friends = cat.getFriends();

        if (!friends.contains(friend)) {
            friends.add(friend);
            friend.getFriends().add(cat);

            catRepository.save(friend);
            catRepository.save(cat);
        }
        else {
             throw new FriendshipException("Cats " + catId + " and " + friendId + " are already friends");
        }
    }

    @Override
    public void removeFriendship(UUID catId, UUID friendId) {
        var catOptional = catRepository.findById(catId);
        var friendOptional = catRepository.findById(friendId);

        if (catOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + catId + " not found");
        }

        if (friendOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + friendId + " not found");
        }

        var cat = catOptional.get();
        var friend = friendOptional.get();
        var friends = cat.getFriends();

        if (friends.contains(friend)) {
            friends.remove(friend);
            friend.getFriends().remove(cat);

            catRepository.save(friend);
            catRepository.save(cat);
        }
        else {
            throw new FriendshipException("Cats " + catId + " and " + friendId + " are not friends");
        }
    }

    @Override
    public List<Cat> getStrayCats() {
        var cats = catRepository.GetStrayCats();

        if (cats.isEmpty()) {
            throw new EntityNotFoundException("No stray cats. Create a new one");
        }

        return cats;
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.getAllCats();
    }

    @Override
    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat update(UUID id, Cat object) {
        var catOptional = catRepository.findById(id);

        if (catOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + id + " not found");
        }

        var cat = catOptional.get();

        return catRepository.save(cat.update(object));
    }

    @Override
    public Cat deletedById(UUID id) {
        Optional<Cat> catOptional = catRepository.findById(id);
        if (catOptional.isPresent()) {
            catRepository.delete(id);
            return catOptional.get();
        } else {
            throw new EntityNotFoundException("Cat with id " + id + " not found");
        }
    }

    @Override
    public Cat findById(UUID id) {
        var catOptional = catRepository.findById(id);

        if (catOptional.isEmpty()) {
            throw new EntityNotFoundException("Cat with id " + id + " not found");
        }

        return catOptional.get();
    }
}
