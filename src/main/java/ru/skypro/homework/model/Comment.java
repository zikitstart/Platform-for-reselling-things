package ru.skypro.homework.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
//Комментарии
public class Comment {

    private Long idComment;   //id комментария
    private User user;   //пользователь
    private Ad ads;   //обьявление
    private LocalDateTime createdAt;   //дата комментария
    private String text;   //текст

}
