package ru.skypro.homework.dto;

import lombok.Data;

@Data
// ДТО пользователя
public class UserDto {
    private long id;            // id пользователя
    private String firstName;   // имя
    private String lastName;    // фамилия
    private String phone;       // телефон
    private String image;       // аватарка пользователя
}
