package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;

public interface UserService {
    void createUser(RegisterUserDto registerReqDto, Role role);
    User getUser(String username);
}
