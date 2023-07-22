package ru.skypro.homework.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Entity
@Table(name = "users")
// Пользователь
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;            //id пользователя
    @Column(name = "user_name")
    private String username;        // логин
    private String password;        // пароль
    @Column(name = "first_name")
    private String firstName;       // имя
    @Column(name = "last_name")
    private String lastName;        // фамилия
    private String phone;           // телефон
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "img_id")
    private Image image;            // ссылка на картинку объявления
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Role role;              // роль: админ/пользователь
}
