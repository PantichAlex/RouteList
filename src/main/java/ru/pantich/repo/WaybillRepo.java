package ru.pantich.repo;

import org.springframework.data.repository.CrudRepository;
import ru.pantich.domain.Waybill;

public interface WaybillRepo extends CrudRepository<Waybill,Long> {
}
