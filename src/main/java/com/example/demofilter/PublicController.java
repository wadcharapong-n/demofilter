package com.example.demofilter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    final JwtService jwtService;

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/token/user")
    public String getTokenUser() {
        UserProfile userProfile = new UserProfile(1L, "menkung@email.test.x", "Menkung", "Iris", List.of("USER"));
        return jwtService.createJwt(userProfile, 60);
    }

    @GetMapping("/token/admin")
    public String getTokenAdmin() {
        UserProfile admin = new UserProfile(2L, "admin@email.test.x", "Admin", "Iris", List.of("ADMIN"));
        return jwtService.createJwt(admin, 60);
    }

    @GetMapping("/token/both")
    public String getTokenBoth() {
        UserProfile admin = new UserProfile(3L, "super@email.test.x", "Super", "Iris", List.of("ADMIN","USER"));
        return jwtService.createJwt(admin, 60);
    }
}
