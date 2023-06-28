package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
//Комментарии
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long idComment;   //id комментария
    @Column(name = "created_at")
    private LocalDateTime createdAt;   //дата комментария
    private String text;   //текст
    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ad ads;   //обьявление
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   //пользователь

}
