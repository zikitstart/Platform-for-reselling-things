package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UserPrincipal;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.UserMapper;

import java.util.Objects;
import java.util.Optional;

@Service
// Сервис для пользователей
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
    // создание пользователя
    public void createUser(RegisterUserDto registerUserDto, Role role) {
        User user = userMapper.registerUserDtoToUser(registerUserDto);
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setRole(Objects.requireNonNullElse(role, Role.USER));
        userRepository.save(user);
    }

    @Override
    // получение текущего пользователя
    public UserDto getCurrentUser(String username) {
        return userMapper.userToUserDto(getUser(username));
    }

    @Override
    // изменение пароля пользователя
    public UserDto setUserPassword(User authUser, NewPasswordDto newPasswordDto) {
        authUser.setPassword(passwordEncoder.encode(newPasswordDto.newPassword));
        userRepository.save(authUser);
        return userMapper.userToUserDto(authUser);
    }

    @Override
    // обновление информации о пользователе
    public void updateUser(User authUser, UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto, authUser);
        userRepository.save(user);
    }

    @Override
    // загрузка аватарки пользователя
    public UserDto loadUserImage(User user, Image image) {
        image.setId(Optional.ofNullable(user.getImage()).map(Image::getId).orElse(null));
        user.setImage(image);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    // проверка корректности пароля
    public boolean isPasswordCorrect(User authUser, String currentPassword) {
        return passwordEncoder.matches(currentPassword, authUser.getPassword());
    }

    @Override
    // получение пользователя
    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        return new UserPrincipal(userMapper.userToUserPrincipalDto(user));
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }
}
