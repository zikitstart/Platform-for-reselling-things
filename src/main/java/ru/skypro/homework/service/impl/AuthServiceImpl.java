package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Service
//Под вопрос
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManagerImpl manager;
  private final PasswordEncoder encoder;
  private final UserService userService;

  public AuthServiceImpl(UserDetailsManagerImpl manager, PasswordEncoder passwordEncoder, UserService userService) {
    this.manager = manager;
    this.encoder = passwordEncoder;
    this.userService = userService;
  }

  @Override
  public boolean login(String username, String password) {
    if (!manager.userExists(username.toLowerCase())) {
      return false;
    }
    UserDetails userDetails = manager.loadUserByUsername(username.toLowerCase());
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterUserDto registerUserDto, Role role) {
    if (manager.userExists(registerUserDto.getUsername())) {
      return false;
    }
    userService.createUser(registerUserDto, role);
    return true;
  }
}
