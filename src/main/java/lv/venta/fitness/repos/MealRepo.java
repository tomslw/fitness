package lv.venta.fitness.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.fitness.models.Meal;

public interface MealRepo extends CrudRepository<Meal, Long>{

}
