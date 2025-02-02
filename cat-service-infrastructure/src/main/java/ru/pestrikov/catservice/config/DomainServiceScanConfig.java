package ru.pestrikov.catservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.pestrikov.catservice.domain.architecture.DomainService;


@Configuration
@ComponentScan(
        basePackages = "ru.pestrikov.catservice.domain",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = DomainService.class)
)
public class DomainServiceScanConfig {
}
