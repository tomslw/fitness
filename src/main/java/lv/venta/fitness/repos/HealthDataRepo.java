package lv.venta.fitness.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.fitness.models.HealthData;

public interface HealthDataRepo extends CrudRepository<HealthData, Long>{

}
