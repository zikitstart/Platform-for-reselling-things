package ru.skypro.homework.model;

import lombok.Data;

import java.util.List;

@Data
//Объявления
public class Ad {

    private long idAd;   //id объявления
    private Image image;   // ссылка на картинку объявления
    private long idUser;   //id пользователя
    private int price;    // цена объявления
    private String title; // заголовок объявления
    private String description;   //описание объявления
    private User user;
    private List<Comment> comments;   //список комментариев

}
