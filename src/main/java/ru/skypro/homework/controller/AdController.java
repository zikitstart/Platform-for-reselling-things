package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateAdDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
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
        ResponseWrapperAdsDto wrapperAdsDto = adsService.getAds();
        return ResponseEntity.ok(wrapperAdsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавить объявление"
    )
    public ResponseEntity<?> createAd(@RequestPart("properties") CreateAdDto properties,
                                      @RequestPart("image") MultipartFile file,
                                      Authentication authentication) throws IOException {
        return ResponseEntity.ok(adsService.createAd(properties, file, authentication));
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
    public ResponseEntity<?> getAdsFromAnAuthorizedUser (Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            ResponseWrapperAdsDto wrapperAdsDto = adsService.getAdsMe(authentication);
            return ResponseEntity.ok(wrapperAdsDto);
        }
    }

    @PatchMapping(value = "/{idAd}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновить картинку объявления"
    )
    public ResponseEntity<AdDto> updateTheAdImage (@PathVariable long idAd, @RequestParam MultipartFile image) throws IOException {
        AdDto adDto = new AdDto();
        return ResponseEntity.ok(adDto);
    }

    // Поиск объявлений по title с IgnoreCase
    @GetMapping("/find-by-title/{searchTitle}")
    public ResponseEntity<?> searchAds(@PathVariable String searchTitle) {
        return ResponseEntity.ok(adsService.findByTitleContainingIgnoreCase(searchTitle));
    }
}
