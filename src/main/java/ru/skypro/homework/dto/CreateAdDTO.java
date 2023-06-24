package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAdDTO {
    private String description;

    private int price;

    private String title;
}
