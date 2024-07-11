package com.example.demofilter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserProfile principal;
    private final Object credentials;

    public JwtAuthenticationToken(UserProfile userProfile, Object credentials) {
        super(AuthorityUtils.createAuthorityList(userProfile.getRoles()));
        this.principal = userProfile;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public UserProfile getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

}
