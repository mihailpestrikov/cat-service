package ru.pestrikov.catservice;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.pestrikov.catservice.mapper.EntityMapper;
import ru.pestrikov.catservice.mapper.EntityMapperImpl;

@TestConfiguration
public class MapperConfiguration {
    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapperImpl();
    }
}
