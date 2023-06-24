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
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateAdDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "CRUD- методы для работы с обьявлениями")
//Контроллер для работы с Объявлениями
public class AdController {

    private final AdsService adsService;

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений"
    )
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds () {
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        return ResponseEntity.ok(responseWrapperAdsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавить объявление"
    )
    public ResponseEntity<AdDto> addAd (@RequestParam String title, @RequestParam String description, @RequestParam long price, @RequestParam MultipartFile image) throws IOException {
        AdDto adDto = new AdDto();
        return ResponseEntity.ok(adDto);
    }

    @GetMapping("/{idAd}")
    @Operation(
            summary = "Получить информацию об объявлении"
    )
    public ResponseEntity<AdDto> getInformationAboutTheAd (@PathVariable long idAd) {
        AdDto adDto = new AdDto();
        return ResponseEntity.ok(adDto);
    }

    @DeleteMapping("/{idAd}")
    @Operation(
            summary = "Удалить объявление"
    )
    public ResponseEntity<Void> deleteAd (@PathVariable long idAd) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{idAd}")
    @Operation(
            summary = "Обновить информацию об объявлении"
    )
    public ResponseEntity<AdDto> updateAdInformation (@PathVariable long idAd, @RequestBody CreateAdDto createAdDto) {
        AdDto adDto = new AdDto();
        return ResponseEntity.ok(adDto);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получить объявления авторизованного пользователя"
    )
    public ResponseEntity<?> getAdsFromAnAuthorizedUser () {
        List<Ad> adList = new ArrayList<>();
        return ResponseEntity.ok(adList);
    }

    @PatchMapping(value = "/{idAd}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновить картинку объявления"
    )
    public ResponseEntity<AdDto> updateTheAdImage (@PathVariable long idAd, @RequestParam MultipartFile image) throws IOException {
        AdDto adDto = new AdDto();
        return ResponseEntity.ok(adDto);
    }
}
