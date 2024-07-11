package com.example.demofilter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserProfile {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    public UserProfile(Long id, String email, String firstName, String lastName, List<String> roles) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}
