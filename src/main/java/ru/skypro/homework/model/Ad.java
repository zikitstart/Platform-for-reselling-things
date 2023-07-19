package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ads")
// Объявления
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ad")
    private Long idAd;               // id объявления
    private String description;      // описание объявления
    private Integer price;           // цена объявления
    private String title;            // заголовок объявления
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "img_id")
    private Image image;             // ссылка на картинку объявления
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;               // пользователь создавший обьявление
}
