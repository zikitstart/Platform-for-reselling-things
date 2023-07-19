package ru.skypro.homework.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
// Картинка
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // id картинки
    @Lob
    @Type(type = "binary")
    private byte[] image;           // картинка в байтах
    @Column(name = "media_type")
    private String mediaType;       // тип картинки

    public String getPath() {
        return "/img/" + this.getId();
    }
}
