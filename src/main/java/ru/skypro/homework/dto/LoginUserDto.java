package ru.skypro.homework.dto;

import lombok.Data;

@Data
//Аутентификация
public class LoginUserDto {
    private String password;
    private String username;
}
