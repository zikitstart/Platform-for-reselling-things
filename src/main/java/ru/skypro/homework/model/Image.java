package ru.skypro.homework.model;

import lombok.Data;

@Data
public class Image {

    private Long id;
    private byte[] image;
    private String mediaType;


    public String getPath() {
        return "/img/" + this.getId();
    }
}
