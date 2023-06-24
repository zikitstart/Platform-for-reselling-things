package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String reqDate;

    private String city;

    private String image;
}
