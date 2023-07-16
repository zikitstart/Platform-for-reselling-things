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
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

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
    private final ImageService imageService;

    private final UserService userService;

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений"
    )
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds () {
        ResponseWrapperAdsDto responseWrapperAdsDto = adsService.findAllAds();
        return ResponseEntity.ok(responseWrapperAdsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавить объявление"
    )
    public ResponseEntity<?> createAd(@RequestPart("properties") CreateAdDto properties,
                                      @RequestPart("image") MultipartFile file,
                                      Authentication authentication) throws IOException {
        Image image = imageService.upload(file);
        return ResponseEntity.ok(adsService.createAd(properties, image, authentication));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить информацию об объявлении"
    )
    public ResponseEntity<FullAdDto> getInformationAboutTheAd(@PathVariable long id) {
        FullAdDto fullAdDto = adsService.getFullAd(id);
        return ResponseEntity.ok(fullAdDto);
    }

    @DeleteMapping("/{idAd}")
    @Operation(
            summary = "Удалить объявление"
    )
    public ResponseEntity<Void> deleteAd (@PathVariable long idAd, Authentication authentication) {
        ResponseEntity<?> response = checkAccess(idAd, authentication);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            adsService.deleteAd(idAd);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PatchMapping(value = "/{idAd}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Обновить информацию об объявлении"
    )
    public ResponseEntity<AdDto> updateAdInformation (@PathVariable long idAd,
                                                      @RequestBody CreateAdDto createAdDto,
                                                      Authentication authentication) {
        ResponseEntity<?> response = checkAccess(idAd, authentication);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            AdDto adDto = adsService.updateAd(idAd, createAdDto);
            return ResponseEntity.ok(adDto);
        }
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получить объявления авторизованного пользователя"
    )
    public ResponseEntity<?> getAdsFromAnAuthorizedUser (Authentication authentication) {
        ResponseWrapperAdsDto responseWrapperAdsDto = adsService.findAdsByUser(authentication.getName());
        return ResponseEntity.ok(responseWrapperAdsDto);
    }

    @PatchMapping(value = "/{idAd}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновить картинку объявления"
    )
    public ResponseEntity<?> updateTheAdImage (@PathVariable long idAd,
                                                   @RequestParam MultipartFile file,
                                                   Authentication authentication) throws IOException {
        ResponseEntity<?> response = checkAccess(idAd, authentication);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            Image image = imageService.upload(file);
            Ad ad = adsService.findAdById(idAd);
            if (image != null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        adsService.updateAdImage(ad, image).getImage().getImage()
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    }

    // Поиск объявлений по title с IgnoreCase
    @GetMapping("/find-by-title/{searchTitle}")
    public ResponseEntity<?> searchAds(@PathVariable String searchTitle) {
        return ResponseEntity.ok(adsService.findByTitleContainingIgnoreCase(searchTitle));
    }

    private ResponseEntity<?> checkAccess(Long id, Authentication authentication) {
        Ad ad = adsService.findAdById(id);

        if (ad == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else if (ad.getUser().getUsername().equals(authentication.getName()) ||
                userService.getUser(authentication.getName()).getRole() == Role.ADMIN) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
