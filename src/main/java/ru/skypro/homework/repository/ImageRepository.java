package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Image;

@Repository
// Репозиторий для картинок
public interface ImageRepository extends JpaRepository<Image, Long> {
}
