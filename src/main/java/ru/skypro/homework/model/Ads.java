package ru.skypro.homework.model;

import lombok.Data;

@Data
//Объявления
public class Ads {

    //id пользователя
    private long idUser;
    //id объявления
    private long idAd;
    //заголовок объявления
    private String title;
    //описание объявления
    private String description;
    //цена объявления
    private long price;

    //фоторгафия для объявления
    private byte[] image;

}
