package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RequestWrapperCommentDto {

    public Integer adId;
    public CommentDto data;
    public String username;

}
