package ru.pantich.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pantich.domain.Car;
import ru.pantich.domain.Driver;
import ru.pantich.domain.RouteSheet;
import ru.pantich.domain.Specialist;

import java.util.List;

public interface RouteSheetRepo extends JpaRepository<RouteSheet, Long> {

    List<RouteSheet> findByDriver(Driver driver);
    List<RouteSheet> findByCar(Car car);
    List<RouteSheet> findBySpecialist(Specialist specialist);
}
