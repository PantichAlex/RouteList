package ru.pantich.repo;

import org.springframework.data.repository.CrudRepository;
import ru.pantich.domain.Car;

import java.util.ArrayList;

public interface CarRepo extends CrudRepository<Car, Long> {
   Car findByNumber(String number);
   ArrayList<Car> findByNameStartsWith(String name);
}
