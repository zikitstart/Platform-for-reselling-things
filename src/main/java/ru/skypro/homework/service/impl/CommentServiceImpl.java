package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.RequestWrapperCommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentsDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.CommentMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, AdRepository adRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
        this.userService = userService;
    }

    @Override
    public ResponseWrapperCommentsDto getComments(RequestWrapperCommentDto requestWrapperCommentDto) {
        List<Comment> commentList = commentRepository
                .findAllByAds_IdAd(commentMapper.adIdFromRequestWrapperDto(requestWrapperCommentDto));
        ResponseWrapperCommentsDto wrapperComment = new ResponseWrapperCommentsDto();
        wrapperComment.setCount(commentList.size());
        wrapperComment.setResults(
                commentList.stream()
                        .map(commentMapper::commentToCommentDto)
                        .collect(Collectors.toList())
        );
        return wrapperComment;
    }

    @Override
    public CommentDto addComment(RequestWrapperCommentDto requestWrapperCommentDto) {
        Long adId = commentMapper.adIdFromRequestWrapperDto(requestWrapperCommentDto);
        Optional<Ad> adOptional =
                adRepository.findById(adId);
        if (adOptional.isEmpty()) {
            return null;
        }
        User user = userService.getUser(requestWrapperCommentDto.getUsername());
        Comment comment = commentMapper.commentFromRequestWrapperDto(requestWrapperCommentDto);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setAds(adOptional.get());

        if (null == findComment(comment)) {
            return commentMapper.commentToCommentDto(
                    commentRepository.save(comment)
            );
        }
        return null;
    }

    private Comment findComment(Comment comment) {
        return commentRepository.findDistinctFirstByTextEqualsAndUserEqualsAndAdsEquals(comment.getText(), comment.getUser(), comment.getAds()).orElse(null);
    }

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isMine(RequestWrapperCommentDto requestWrapperCommentDto) {
        Long id = commentMapper.commentFromRequestWrapperDto(requestWrapperCommentDto).getIdComment();
        Comment comment = findCommentById(id);
        if (null == comment) {
            return false;
        }
        return comment.getUser().getUsername().equals(requestWrapperCommentDto.getUsername());
    }


    @Override
    public CommentDto deleteComment(RequestWrapperCommentDto requestWrapperCommentDto) {
        Long id = commentMapper.commentFromRequestWrapperDto(requestWrapperCommentDto).getIdComment();
        Comment comment = findCommentById(id);
        if (null == comment) {
            return null;
        }
        commentRepository.deleteById(id);
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(RequestWrapperCommentDto requestWrapperCommentDto) {
        Long id = commentMapper.commentFromRequestWrapperDto(requestWrapperCommentDto).getIdComment();
        Comment comment = findCommentById(id);
        if (null == comment) {
            return null;
        }
        if (commentMapper.commentFromRequestWrapperDto(requestWrapperCommentDto).getText().equals(comment.getText())) {
            return null;
        }
        comment.setText(commentMapper.commentFromRequestWrapperDto(requestWrapperCommentDto).getText());
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }
}
