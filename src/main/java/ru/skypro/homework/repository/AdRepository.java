package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    @Query(value = "select * from ads order by id_ad", nativeQuery = true)
    List<Ad> findAllAds();
    List<Ad> findAdsByUserIdUser (Long id);

    List<Ad> findByTitleLikeIgnoreCase(String s);

    Ad findAdsByIdAd(Long id);
    Ad findAdByIdAd(Long id);
}
