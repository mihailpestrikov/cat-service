package ru.pestrikov.catservice.CustomUserSetup;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import ru.pestrikov.catservice.domain.model.User;
import ru.pestrikov.catservice.resource.auth.UserDetailsImpl;

import java.util.UUID;

public class WithMockCustomUserSecurityContextFactory
        implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UserDetailsImpl principal =
                new UserDetailsImpl(
                        new User(UUID.fromString("4e40147f-25d2-4bb2-bf52-25563ec8522d"),
                                "user",
                                "password",
                                CatOwnerSetup.owner
                                ));
        Authentication auth =
                UsernamePasswordAuthenticationToken.authenticated(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
