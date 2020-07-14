package ru.pantich.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pantich.domain.Driver;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, Long> {
    List<Driver> findByName(String name);
    List<Driver> findBySurname(String surname);
    List<Driver> findByPatronymic(String patronymic);

}
