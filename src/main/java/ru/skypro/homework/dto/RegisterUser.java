package ru.skypro.homework.dto;

import lombok.Data;

@Data
//Регистрация нового пользователя
public class RegisterUser {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Role role;
}
