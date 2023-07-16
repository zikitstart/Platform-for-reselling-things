package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findDistinctFirstByTextEqualsAndUserEqualsAndAdsEquals(String text, User user, Ad ads);
    List<Comment> findAllByAds_IdAd(Long ads_id);
}
