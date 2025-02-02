package ru.pestrikov.catservice.domain.in.application;

import ru.pestrikov.catservice.domain.model.Owner;

import java.util.UUID;

public interface OwnerService extends BaseService<Owner> {
    void makeOwnership(UUID ownerId, UUID catId);
    void removeOwnership(UUID ownerId, UUID catId);
}
