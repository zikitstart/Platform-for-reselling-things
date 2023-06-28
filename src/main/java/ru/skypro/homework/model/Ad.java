package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads")
//Объявления
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ad")
    private long idAd;   //id объявления
    private String description;   //описание объявления
    private int price;    // цена объявления
    private String title; // заголовок объявления
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "img_id")
    private Image image;   // ссылка на картинку объявления
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;   //список комментариев

    public Ad(String description, int price, String title, Image image, User user) {
        this.description = description;
        this.price = price;
        this.title = title;
        this.image = image;
        this.user = user;
    }

    public Ad(long idAd, String description, int price, String title, Image image, User user) {
        this.idAd = idAd;
        this.description = description;
        this.price = price;
        this.title = title;
        this.image = image;
        this.user = user;
    }
}
