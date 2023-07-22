package ru.skypro.homework.dto;

import lombok.Data;

@Data
// ДТО комментария
public class CommentDto {
    private Integer author;            // id автора комментария
    private String authorImage;          // ссылка на аватар автора комментария
    private String authorFirstName;      // имя создателя комментария
    private Long createdAt;              // дата и время создания комментария
    private Long pk;                     // id комментария
    private String text;                 // текст комментария
}
