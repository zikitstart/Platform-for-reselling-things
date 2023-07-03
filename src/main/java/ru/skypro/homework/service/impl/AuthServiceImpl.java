package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;

@Service
//Под вопрос
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManagerImpl manager;

  private final PasswordEncoder encoder;

  public AuthServiceImpl(UserDetailsManagerImpl manager, PasswordEncoder passwordEncoder) {
    this.manager = manager;
    this.encoder = passwordEncoder;
  }

  @Override
  public boolean login(String userName, String password) {
    if (!manager.userExists(userName)) {
      return false;
    }
    UserDetails userDetails = manager.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterUserDto registerUserDto, Role role) {
    if (manager.userExists(registerUserDto.getUsername())) {
      return false;
    }
    manager.createUser(
        User.builder()
            .passwordEncoder(this.encoder::encode)
            .password(registerUserDto.getPassword())
            .username(registerUserDto.getUsername())
            .roles(role.name())
            .build());
    return true;
  }
}
