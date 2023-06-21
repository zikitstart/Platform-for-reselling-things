package ru.skypro.homework.model;

import lombok.Data;

@Data
//Комментарии
public class Comment {

    //id пользователя
    private long idUser;
    //аватарка пользователя
    private byte[] profilePicture;
    //имя пользователя
    private String firstName;
    //id комментария
    private long idComment;
    //id объявления
    private long idAd;
    //текст объявления
    private String text;
}
