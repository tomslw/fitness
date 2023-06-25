package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.Ingredient;

public interface IIngredientService {
	ArrayList<Ingredient> selectAllIngredients();
	void insertNewIngredient(String title, String description, int calories, int fat, int carbohydrates, int protein);
	void deleteIngredientById(long idin);
}
