package lv.venta.fitness.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.fitness.models.Exercise;

public interface ExerciseRepo extends CrudRepository<Exercise, Long>{

}
