package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
//Пользователь
public class User {

    //id пользователя
    private long idUser;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    //аватарка пользователя
    private byte[] profilePicture;
    //роль: админ/пользователь
    private Role role;
}
