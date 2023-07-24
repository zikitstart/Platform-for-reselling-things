package ru.skypro.homework.utils;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserPrincipalDto;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Component
// Маппер для пользователя
public class UserMapper {

    public UserDto userToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getIdUser());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setImage(Optional.ofNullable(user.getImage()).map(Image::getPath).orElse(null));
        return dto;
    }

    public User userDtoToUser(UserDto userDto,User user) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        return user;
    }

    public User registerUserDtoToUser(RegisterUserDto registerUserDto) {
        User user = new User();
        user.setUsername(registerUserDto.getUsername().toLowerCase());
        user.setPassword(registerUserDto.getPassword());
        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setPhone(registerUserDto.getPhone());
        user.setRole(registerUserDto.getRole());
        return user;
    }

    public UserPrincipalDto userToUserPrincipalDto(User user) {
        return new UserPrincipalDto()
                .withId(user.getIdUser())
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withRole(user.getRole());
    }
}