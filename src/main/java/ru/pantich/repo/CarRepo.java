package ru.pantich.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pantich.domain.Car;
import ru.pantich.domain.Specialist;
import ru.pantich.domain.User;

import java.util.ArrayList;

public interface CarRepo extends JpaRepository<Car, Long> {
   Car findByNumber(String number);
   ArrayList<Car> findByNameStartsWith(String name);

}
