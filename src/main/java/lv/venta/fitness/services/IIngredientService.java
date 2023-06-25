package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.Ingredient;

public interface IIngredientService {
	ArrayList<Ingredient> selectAllIngredients();
	void deleteIngredientById(long idin);
}
