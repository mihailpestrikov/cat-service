package ru.pestrikov.catservice.out.persistance.config;

import jakarta.transaction.Transactional;

import java.util.function.Supplier;

public class TransactionalDomainServiceExecutor {
    @Transactional
    public <T> T executeInTransaction(Supplier<T> execution) {
        return execution.get();
    }
}
