package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

public interface ImageService {
    Image upload(MultipartFile imageFile) throws IOException;

    Image save(Image image);

    Image read(long id);
}
