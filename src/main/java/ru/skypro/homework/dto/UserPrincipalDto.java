package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipalDto {
    private Long id;

    private String username;

    private String password;

    private Role role;

    public UserPrincipalDto withId(Long id) {
        this.id = id;
        return this;
    }

    public UserPrincipalDto withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserPrincipalDto withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserPrincipalDto withRole(Role role) {
        this.role = role;
        return this;
    }
}
