package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.AdsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "CRUD- методы для работы с обьявлениями")
//Контроллер для работы с Объявлениями
public class AdsController {

    private final AdsService adsService;

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений"
    )
    public ResponseEntity<?> getAllAds () {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    @Operation(
            summary = "Добавить объявление"
    )
    //в параметрах будет DTO класс от ADS
    public ResponseEntity<?> addAd () {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{idAd}")
    @Operation(
            summary = "Получить информацию об объявлении"
    )
    public ResponseEntity<?> getInformationAboutTheAd (@PathVariable long idAd) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idAd}")
    @Operation(
            summary = "Удалить объявление"
    )
    public ResponseEntity<?> deleteAd (@PathVariable long idAd) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{idAd}")
    @Operation(
            summary = "Обновить информацию об объявлении"
    )
    //второй параметр DTO класса ADS
    public ResponseEntity<?> updateAdInformation (@PathVariable long idAd) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получить объявления авторизованного пользователя"
    )
    public ResponseEntity<?> getAdsFromAnAuthorizedUser () {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{idAd}/image",consumes = MediaType.IMAGE_JPEG_VALUE)
    @Operation(
            summary = "Обновить картинку объявления"
    )
    public ResponseEntity<?> updateTheAdImage (@PathVariable long idAd, @RequestParam MultipartFile image) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
