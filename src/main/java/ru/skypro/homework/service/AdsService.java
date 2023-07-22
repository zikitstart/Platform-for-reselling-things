package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateAdDto;
import ru.skypro.homework.dto.FullAdDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;

import java.io.IOException;
import java.util.List;

public interface AdsService {
    AdDto createAd(CreateAdDto properties, Image image, Authentication authentication) throws IOException;

    List<AdDto> findByTitleContainingIgnoreCase(String searchTitle);

    ResponseWrapperAdsDto findAdsByUser(String username);

    ResponseWrapperAdsDto findAllAds();

    FullAdDto getFullAd(long id);

    Ad findAdById(Long id);

    int deleteAd(Long id);

    AdDto updateAd(long id, CreateAdDto createAdDto);

    Ad updateAdImage(Ad ad, Image image);

    Ad getAdById(long id);
}
