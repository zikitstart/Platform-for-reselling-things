package ru.skypro.homework.dto;

import lombok.Data;

@Data
//Аутентификация
public class LoginUser {
    private String login;
    private String password;
}
