package ru.pantich.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pantich.domain.Specialist;
import ru.pantich.domain.User;

public interface SpecialistRepo extends JpaRepository<Specialist,Long> {
    Specialist findByUser(User user);
}
