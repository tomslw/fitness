package lv.venta.fitness.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.fitness.models.HealthData;

public interface HealthDataRepo extends CrudRepository<HealthData, Long>{

	ArrayList<HealthData> findAllByUserIdus(long idus);

	void deleteByUserIdus(long idus);
	
	HealthData findTopByOrderByDate();

	HealthData findbyIdhe(long idhe);
}
