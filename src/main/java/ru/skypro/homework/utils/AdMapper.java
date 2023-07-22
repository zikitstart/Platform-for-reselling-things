package ru.skypro.homework.utils;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateAdDto;
import ru.skypro.homework.dto.FullAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
// Маппер для объявления
public class AdMapper {

    public AdDto adToAdDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setAuthor(ad.getUser().getIdUser());
        adDto.setImage(Optional.ofNullable(ad.getImage()).map(Image::getPath).orElse(null));
        adDto.setPk(ad.getIdAd());
        adDto.setPrice(ad.getPrice());
        adDto.setTitle(ad.getTitle());
        return adDto;
    }

    public FullAdDto adsToFullAds(Ad ad) {
        if (ad == null) {
            return null;
        }
        FullAdDto fullAdDto = new FullAdDto();
        fullAdDto.setPk(ad.getIdAd());
        fullAdDto.setAuthorFirstName(ad.getUser().getFirstName());
        fullAdDto.setAuthorLastName(ad.getUser().getLastName());
        fullAdDto.setDescription(ad.getDescription());
        fullAdDto.setEmail(ad.getUser().getUsername());
        fullAdDto.setImage(Optional.ofNullable(ad.getImage()).map(Image::getPath).orElse(null));
        fullAdDto.setPhone(ad.getUser().getPhone());
        fullAdDto.setPrice((ad.getPrice()));
        fullAdDto.setTitle(ad.getTitle());
        return fullAdDto;
    }

    public Ad CreateAdDtoToAds(CreateAdDto createAdDto) {
        Ad ad = new Ad();
        ad.setDescription(createAdDto.getDescription());
        ad.setPrice(createAdDto.getPrice());
        ad.setTitle(createAdDto.getTitle());
        return ad;
    }

    public Ad createAdsToAds(Ad ad, CreateAdDto createAdDto){
        ad.setDescription(isNull(createAdDto.getDescription()) ? ad.getDescription() : createAdDto.getDescription());
        ad.setPrice(isNull(createAdDto.getPrice()) ? ad.getPrice() : createAdDto.getPrice());
        ad.setTitle(isNull(createAdDto.getTitle()) ? ad.getTitle() : createAdDto.getTitle());
        return ad;
    }
}