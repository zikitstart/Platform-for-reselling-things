package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
// Ответ комментария
public class ResponseWrapperCommentsDto {
    public int count;                   // счетчик
    public List<CommentDto> results;    // список ДТО комментария
}
