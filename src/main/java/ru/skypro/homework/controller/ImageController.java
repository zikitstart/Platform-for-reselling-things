package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.service.ImageService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/img")
@Tag(name = "Картинки", description = "Методы для работы с картинками")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(
            tags = "Картинки",
            summary = "Получить картинку"
    )
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Image imageModel = imageService.read(id);
        if (null != imageModel) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(imageModel.getMediaType()));
            headers.setContentLength(imageModel.getImage().length);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageModel.getImage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
