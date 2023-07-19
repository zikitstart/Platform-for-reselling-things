package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateAdDto;
import ru.skypro.homework.dto.FullAdDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.utils.AdMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// Сервис для объявлений
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public AdsServiceImpl(AdRepository adRepository, AdMapper adMapper, UserRepository userRepository, ImageRepository imageRepository) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    // создание объявления
    public AdDto createAd(CreateAdDto properties, Image image, Authentication authentication) {
        Ad ad = adMapper.CreateAdDtoToAds(properties);
        ad.setUser(userRepository.findUserByUsername(authentication.getName()).orElseThrow());
        imageRepository.save(image);
        ad.setImage(image);
        adRepository.save(ad);
        return adMapper.adToAdDto(ad);
    }

    @Override
    // поиск объявления по заголовку
    public List<AdDto> findByTitleContainingIgnoreCase(String searchTitle) {
        List<Ad> ad = adRepository.findByTitleLikeIgnoreCase(searchTitle);
        return ad.stream().map(adMapper::adToAdDto).collect(Collectors.toList());
    }

    @Override
    // поиск объявлений пользователя
    public ResponseWrapperAdsDto findAdsByUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        List<Ad> ads = adRepository.findAdsByUserIdUser(user.getIdUser());
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        responseWrapperAdsDto.setCount(ads.size());
        responseWrapperAdsDto.setResults(ads.stream().map(adMapper::adToAdDto).collect(Collectors.toList()));
        return responseWrapperAdsDto;
    }

    @Override
    // поиск всех объявлений
    public ResponseWrapperAdsDto findAllAds() {
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        List<Ad> ads = adRepository.findAllAds();
        responseWrapperAdsDto.setCount(ads.size());
        responseWrapperAdsDto.setResults(ads.stream().map(adMapper::adToAdDto).collect(Collectors.toList()));
        return responseWrapperAdsDto;
    }

    @Override
    // получение полного объявления по id
    public FullAdDto getFullAd(long id) {
        Ad ad = adRepository.findAdByIdAd(id);
        return adMapper.adsToFullAds(ad);
    }

    @Override
    // поиск объявления по id
    public Ad findAdById(Long id) {
        return adRepository.findAdsByIdAd(id);
    }

    @Override
    // удаление объявления по id
    public int deleteAd(Long id) {
        if (adRepository.findById(id).isEmpty()) {
            return 204; // не найден
        } else {
            adRepository.deleteById(id);
            return 0; // запись удалена
        }
    }

    @Override
    // обновление информации объявления
    public AdDto updateAd(long id, CreateAdDto createAdDto) {
        Optional<Ad> adOptional = adRepository.findById(id);
        if (adOptional.isEmpty()) {
            return null;
        }
        Ad ad = adMapper.createAdsToAds(adOptional.get(), createAdDto);
        adRepository.save(ad);
        return adMapper.adToAdDto(ad);
    }

    @Override
    // обновление картинки объявления
    public Ad updateAdImage(Ad ad, Image image) {
        image.setId(Optional.ofNullable(ad.getImage()).map(Image::getId).orElse(null));
        ad.setImage(image);
        return adRepository.saveAndFlush(ad);
    }

    @Override
    // получение объявления по id
    public Ad getAdById(long id) {
        return adRepository.findById(id).orElse(null);
    }
}
