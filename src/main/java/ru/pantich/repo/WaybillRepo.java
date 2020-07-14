package ru.pantich.repo;

import org.springframework.data.repository.CrudRepository;
import ru.pantich.domain.RouteSheet;
import ru.pantich.domain.Waybill;

import java.util.List;

public interface WaybillRepo extends CrudRepository<Waybill,Long> {
    Waybill findByRouteSheet(RouteSheet routeSheet);
}
