package ru.pestrikov.catservice.out.persistance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TransactionalDomainServiceConfiguration {
    @Bean
    TransactionalDomainServiceAspect transactionalUseCaseAspect(TransactionalDomainServiceExecutor transactionalDomainServiceExecutor) {
        return new TransactionalDomainServiceAspect(transactionalDomainServiceExecutor);
    }

    @Bean
    TransactionalDomainServiceExecutor transactionalUseCaseExecutor() {
        return new TransactionalDomainServiceExecutor();
    }
}