package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.LoginUserDto;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Методы для регистрации и авторизации пользователя")
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "Авторизация пользователя"
    )
    public ResponseEntity<?> login(@RequestBody LoginUserDto log) {
        if (authService.login(log.getUsername(), log.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/register")
    @Operation(
            summary = "Регистрация пользователя"
    )
    public ResponseEntity<?> register(@RequestBody RegisterUserDto userDto) {
        Role role = userDto.getRole() == null ? Role.USER : userDto.getRole();
        if (authService.register(userDto, role)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
