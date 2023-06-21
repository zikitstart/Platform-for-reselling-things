package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Комментарии", description = "CRUD- методы для работы с комментариями")
//Контроллер для работы с Комментариями
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{idAd}/comments")
    @Operation(
            summary = "Получить комментарии объявления"
    )
    public ResponseEntity<?> getAdComments (@PathVariable long idAd) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{idAd}/comments")
    @Operation(
            summary = "Добавить комментарий к объявлению"
    )
    public ResponseEntity<?> addCommentToAd (@PathVariable long idAd, @RequestParam String text) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idAd}/comments/{idComment}")
    @Operation(
            summary = "Удалить комментарий"
    )
    public ResponseEntity<?> deleteCommentFromAd (@PathVariable long idAd, @PathVariable long idComment) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{idAd}/comments/{idComment}")
    @Operation(
            summary = "Обновить комментарий"
    )
    private ResponseEntity<?> updateComment (@PathVariable long idAd, @PathVariable long idComment) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
