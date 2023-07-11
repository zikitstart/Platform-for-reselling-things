package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateAdDto;

import java.io.IOException;
import java.util.List;

public interface AdsService {
    AdDto createAd(CreateAdDto properties, MultipartFile file, Authentication authentication) throws IOException;

    List<AdDto> findByTitleContainingIgnoreCase(String searchTitle);
}
