package ru.pantich.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pantich.domain.User;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
