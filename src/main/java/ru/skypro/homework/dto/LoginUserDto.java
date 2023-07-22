package ru.skypro.homework.dto;

import lombok.Data;

@Data
// Аутентификация
public class LoginUserDto {
    private String password;    // пароль
    private String username;    // логин
}
