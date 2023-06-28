package ru.skypro.homework.utils;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class CommentMapper {
    private final UserRepository userRepository;

    public CommentMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(Math.toIntExact(comment.getIdComment()));
        commentDto.setAuthorId(Math.toIntExact(comment.getUser().getIdUser()));
        commentDto.setAuthorImage(Optional.ofNullable(comment.getUser())
                .map(User::getImage)
                .map(Image::getPath)
                .orElse(null));
        commentDto.setAuthorFirstName(comment.getUser().getFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt().toInstant(ZoneOffset.ofHours(3)).toEpochMilli());
        commentDto.setText(comment.getText());
        return commentDto;
    }

    public Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setIdComment(commentDto.getPk().longValue());
        comment.setCreatedAt(LocalDateTime.ofEpochSecond(commentDto.getCreatedAt(),0,ZoneOffset.ofHours(3)));
        comment.setText(commentDto.getText());
        comment.setUser(userRepository
                .findById(commentDto.getAuthorId().longValue())
                .orElse(null));
        return comment;
    }
}