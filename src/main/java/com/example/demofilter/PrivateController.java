package com.example.demofilter;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping("/user")
    @Secured({"USER"})
    public String helloUser() {
        return "Hello World private !!!!!";
    }

    @GetMapping("/admin")
    @Secured({"ADMIN"})
    public String helloAdmin() {
        return "Hello World private admin !!!!!";
    }
}
