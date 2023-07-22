package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.RequestWrapperCommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentsDto;

public interface CommentService {
    ResponseWrapperCommentsDto getComments(RequestWrapperCommentDto requestWrapperCommentDto);

    CommentDto addComment(RequestWrapperCommentDto requestWrapperCommentDto);

    boolean isMine(RequestWrapperCommentDto requestWrapperCommentDto);

    CommentDto deleteComment(RequestWrapperCommentDto requestWrapperCommentDto);

    CommentDto updateComment(RequestWrapperCommentDto requestWrapperCommentDto);
}
