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

    @GetMapping("/token")
    public String getToken() {
        UserProfile userProfile = new UserProfile(1L, "menkung@email.test.x", "Menkung", "Iris", List.of("USER"));
        return jwtService.createJwt(userProfile, 60);
    }
}
