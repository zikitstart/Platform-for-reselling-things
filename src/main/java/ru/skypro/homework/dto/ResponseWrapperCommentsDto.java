package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperCommentsDto {

    public int count;
    public List<CommentDto> results;

}
