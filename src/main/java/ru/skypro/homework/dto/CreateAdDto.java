package ru.skypro.homework.dto;

import lombok.Data;

@Data
// ДТО создания объявления
public class CreateAdDto {
    private String description;     // описание объявления
    private Integer price;          // цена объявления
    private String title;           // заголовок объявления
}
