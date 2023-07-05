package ru.skypro.homework.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Type(type = "binary")
    private byte[] image;
    @Column(name = "media_type")
    private String mediaType;

    public String getPath() {
        return "/img/" + this.getId();
    }
}
