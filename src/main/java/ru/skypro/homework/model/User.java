package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
//Пользователь
public class User {

    private Long idUser;   //id пользователя
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Image image;   // ссылка на картинку объявления
    private Role role;   //роль: админ/пользователь
}
