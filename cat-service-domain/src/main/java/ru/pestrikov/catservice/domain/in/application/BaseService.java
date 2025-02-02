package ru.pestrikov.catservice.domain.in.application;

import java.util.UUID;

public interface BaseService<T> {
    T create(T object);
    T update(UUID id, T object);
    T deletedById(UUID id);
    T findById(UUID id);
}
