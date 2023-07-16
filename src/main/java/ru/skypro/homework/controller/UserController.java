package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "CRUD- методы для работы с пользователями")
//Контроллер для работы с Пользователями
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @PostMapping("/set-password")
    @Operation(
            summary = "Обновление пароля"
    )
    public ResponseEntity<?> passwordUpdate (@RequestBody NewPasswordDto newPasswordDto, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        if (!userService.isPasswordCorrect(user, newPasswordDto.currentPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDto modifiedUserDto = userService.setUserPassword(user, newPasswordDto);
        return ResponseEntity.ok(modifiedUserDto);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получить информацию об авторизованном пользователе"
    )
    public ResponseEntity<?> getUser(Authentication authentication) {
        UserDto authUser = userService.getCurrentUser(authentication.getName());
        return ResponseEntity.ok(authUser);
    }

    @PatchMapping("/me")
    @Operation(
            summary = "Обновить информацию об авторизованном пользователе"
    )
    public ResponseEntity<?> updateInformationAboutAnAuthorizedUser(@RequestBody UserDto userDto, Authentication authentication) {
        User authUser = userService.getUser(authentication.getName());
        userService.updateUser(authUser, userDto);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping(value = "/me/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновить аватар авторизованного пользователя"
    )
    public ResponseEntity<Void> updateTheAvatarOfAnAuthorizedUser(@RequestPart("image") MultipartFile image, Authentication authentication) throws IOException {
        User authUser = userService.getUser(authentication.getName());
        userService.loadUserImage(authUser, imageService.upload(image));
        return ResponseEntity.ok().build();
    }
}
