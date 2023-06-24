package ru.skypro.homework.dto;

import lombok.Data;

@Data
//Регистрация нового пользователя
public class RegisterUserDto {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
