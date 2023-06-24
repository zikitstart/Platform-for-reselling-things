package ru.skypro.homework.dto;

import lombok.Data;

@Data
//Аутентификация
public class LoginUserDto {
    private String userName;
    private String password;
}
