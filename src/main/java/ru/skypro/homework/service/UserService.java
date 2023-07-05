package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;

public interface UserService {
    void createUser(RegisterUserDto registerReqDto, Role role);
}
