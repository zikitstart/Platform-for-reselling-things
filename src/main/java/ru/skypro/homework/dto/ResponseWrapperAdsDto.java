package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
// Ответ объявления
public class ResponseWrapperAdsDto {
    public int count;               // счетчик
    public List<AdDto> results;     // список ДТО объявлений
}
