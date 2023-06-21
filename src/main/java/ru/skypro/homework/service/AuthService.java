package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterUser;
import ru.skypro.homework.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterUser registerUser, Role role);
}
