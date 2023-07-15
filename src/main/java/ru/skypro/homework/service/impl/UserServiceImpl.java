package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.UserMapper;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper userMapper, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(RegisterUserDto registerUserDto, Role role) {
        User user = userMapper.registerUserDtoToUser(registerUserDto);
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setRole(Objects.requireNonNullElse(role, Role.USER));
        userRepository.save(user);
    }

    @Override
    public UserDto getCurrentUser(String username) {
        return userMapper.userToUserDto(getUser(username));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElseThrow();
    }
}
