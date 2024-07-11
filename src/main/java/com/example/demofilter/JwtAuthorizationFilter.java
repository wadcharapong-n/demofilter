package com.example.demofilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    final JwtService jwtService;

    public JwtAuthorizationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }
        token = token.substring(7); // split Bearer
        Claims claims = jwtService.getClaims(token);
        Integer userId = (Integer) claims.get("I_USER");
        String firstName = (String) claims.get("F_NAME");
        String lastName = (String) claims.get("L_NAME");
        String email = (String) claims.get("U_EMAIL");
        List<String> roles = (List<String>) claims.get("U_ROLES");
        UserProfile userProfile = new UserProfile(Long.valueOf(userId), email, firstName, lastName, roles);

        JwtAuthenticationToken authentication = new JwtAuthenticationToken(userProfile, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
}
