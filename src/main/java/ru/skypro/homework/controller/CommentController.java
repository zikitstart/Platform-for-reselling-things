package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.RequestWrapperCommentDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Комментарии", description = "CRUD- методы для работы с комментариями")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получить комментарии объявления"
    )
    public ResponseEntity<?> getAdComments(@PathVariable Integer id) {
        RequestWrapperCommentDto requestWrapperCommentDto = new RequestWrapperCommentDto();
        requestWrapperCommentDto.setAdId(id);
        return ResponseEntity.ok(commentService.getComments(requestWrapperCommentDto));
    }

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавить комментарий к объявлению"
    )
    public ResponseEntity<?> addCommentToAd(@PathVariable Integer id, @RequestBody CommentDto comment, Authentication authentication) {
        return ResponseEntity.ok(commentService.addComment(requestWrapperCommentDto(id, comment, authentication)));
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Удалить комментарий"
    )
    public ResponseEntity<?> deleteCommentFromAd(@PathVariable Integer adId, @PathVariable Long commentId, Authentication authentication) {
        CommentDto comment = new CommentDto();
        comment.setPk(commentId);
        comment = commentService.isMine(requestWrapperCommentDto(adId, comment, authentication)) || userService.getUser(authentication.getName()).getRole() == Role.ADMIN ? commentService.deleteComment(requestWrapperCommentDto(adId, comment, authentication)) : null;
        if (null == comment) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Обновить комментарий"
    )
    private ResponseEntity<?> updateComment(@PathVariable Integer adId, @PathVariable Long commentId, @RequestBody CommentDto comment, Authentication authentication) {
        comment.setPk(commentId);
        comment = commentService.isMine(requestWrapperCommentDto(adId, comment, authentication)) || userService.getUser(authentication.getName()).getRole() == Role.ADMIN ? commentService.updateComment(requestWrapperCommentDto(adId, comment, authentication)) : null;
        if (null == comment) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(comment);
    }

    private RequestWrapperCommentDto requestWrapperCommentDto(Integer id, CommentDto commentDto, Authentication authentication) {
        RequestWrapperCommentDto requestWrapperCommentDto = new RequestWrapperCommentDto();
        requestWrapperCommentDto.setAdId(id);
        requestWrapperCommentDto.setData(commentDto);
        requestWrapperCommentDto.setUsername(authentication.getName());
        return requestWrapperCommentDto;
    }
}
