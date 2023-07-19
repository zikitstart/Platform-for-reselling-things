package ru.skypro.homework.dto;

import lombok.Data;

@Data
// Запрос комментария
public class RequestWrapperCommentDto {
    public Integer adId;        // id объявления
    public CommentDto data;     // ДТО комментария
    public String username;     // логин
}
