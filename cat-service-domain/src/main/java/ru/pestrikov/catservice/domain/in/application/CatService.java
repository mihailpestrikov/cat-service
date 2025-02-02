package ru.pestrikov.catservice.domain.in.application;

import ru.pestrikov.catservice.domain.model.Cat;

import java.util.List;
import java.util.UUID;

public interface CatService extends BaseService<Cat> {
    void makeFriendship(UUID firstCatId, UUID secondCatId);
    void removeFriendship(UUID firstCatId, UUID secondCatId);
    List<Cat> getStrayCats();
    List<Cat> getAllCats();
}
