package ru.skypro.homework.dto;

import lombok.Data;

@Data
// Регистрация нового пользователя
public class RegisterUserDto {
    private String username;    // логин
    private String password;    // пароль
    private String firstName;   // имя
    private String lastName;    // фамилия
    private String phone;       // телефон
    private Role role;          // роль: админ/пользователь
}
