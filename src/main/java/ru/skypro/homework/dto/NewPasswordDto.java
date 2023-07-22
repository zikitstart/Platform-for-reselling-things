package ru.skypro.homework.dto;

import lombok.Data;

@Data
// Смена пароля
public class NewPasswordDto {
    public String currentPassword;      // старый пароль
    public String newPassword;          // новый пароль
}
