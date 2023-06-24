package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDto {

    public String currentPassword;
    public String newPassword;

}
