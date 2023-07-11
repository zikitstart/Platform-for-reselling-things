package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users order by id_user", nativeQuery = true)
    List<User> findAllUsers();
    Optional<User> findUserByUsername(String username);
    Optional<User> getUserByUsernameIgnoreCase(String username);
}
