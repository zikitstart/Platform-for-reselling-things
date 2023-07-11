package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "CRUD- методы для работы с пользователями")
//Контроллер для работы с Пользователями
public class UserController {

    private final UserService userService;

    @PostMapping("/set-password")
    @Operation(
            summary = "Обновление пароля"
    )
    public ResponseEntity<Void> passwordUpdate (@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получить информацию об авторизованном пользователе"
    )
    public ResponseEntity<UserDto> getInformationAboutAnAuthorizedUser () {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/me")
    @Operation(
            summary = "Обновить информацию об авторизованном пользователе"
    )
    public ResponseEntity<UserDto> updateInformationAboutAnAuthorizedUser (@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/me/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновить аватар авторизованного пользователя"
    )
    public ResponseEntity<Void> updateTheAvatarOfAnAuthorizedUser (@RequestParam MultipartFile profilePicture) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
