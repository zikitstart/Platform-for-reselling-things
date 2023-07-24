package ru.skypro.homework.service;

import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

public interface UserService extends UserDetailsManager {
    void createUser(RegisterUserDto registerReqDto, Role role);

    UserDto getCurrentUser(String username);

    UserDto setUserPassword(User authUser, NewPasswordDto newPasswordDto);

    void updateUser(User authUser, UserDto userDto);

    UserDto loadUserImage(User user, Image image);

    boolean isPasswordCorrect(User authUser, String currentPassword);

    User getUser(String username);
}
