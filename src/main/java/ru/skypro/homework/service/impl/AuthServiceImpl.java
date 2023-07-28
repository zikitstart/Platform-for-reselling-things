package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Service
// Сервис для авторизации
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;

    private final UserService userService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserService userService) {
        this.encoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    // Авторизация пользователя
    public boolean login(String username, String password) {
        if (!userService.userExists(username.toLowerCase())) {
            return false;
        }
        UserDetails userDetails = userService.loadUserByUsername(username.toLowerCase());
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    // Регистрация пользователя
    public boolean register(RegisterUserDto registerUserDto, Role role) {
        if (userService.userExists(registerUserDto.getUsername())) {
            return false;
        }
        userService.createUser(registerUserDto, role);
        return true;
    }
}
