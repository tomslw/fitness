package lv.venta.fitness.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.fitness.models.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Long>{

}
