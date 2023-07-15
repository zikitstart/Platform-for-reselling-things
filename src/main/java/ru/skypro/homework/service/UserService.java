package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

public interface UserService {
    void createUser(RegisterUserDto registerReqDto, Role role);

    UserDto getCurrentUser(String username);

    UserDto setUserPassword(User authUser, NewPasswordDto newPasswordDto);

    boolean isPasswordCorrect(User authUser, String currentPassword);

    User getUser(String username);
}
