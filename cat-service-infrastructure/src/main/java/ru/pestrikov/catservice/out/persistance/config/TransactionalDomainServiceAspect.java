package ru.pestrikov.catservice.out.persistance.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ru.pestrikov.catservice.domain.architecture.DomainService;

@Aspect
@RequiredArgsConstructor
public class TransactionalDomainServiceAspect {
    private final TransactionalDomainServiceExecutor transactionalDomainServiceExecutor;

    @Pointcut("@within(domainService)")
    public void inDomainService(DomainService domainService) {

    }

    @Around(value = "inDomainService(domainService)", argNames = "proceedingJoinPoint,domainService")
    public Object useCase(ProceedingJoinPoint proceedingJoinPoint, DomainService domainService) {
        return transactionalDomainServiceExecutor.executeInTransaction(() -> proceed(proceedingJoinPoint));
    }

    @SneakyThrows
    private static Object proceed(ProceedingJoinPoint joinPoint) {
        return joinPoint.proceed();
    }
}
