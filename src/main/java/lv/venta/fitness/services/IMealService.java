package lv.venta.fitness.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.models.Ingredient;
import lv.venta.fitness.models.Meal;

public interface IMealService {
	ArrayList<Meal> selectAllMeals();
	ArrayList<Meal> selectMealsWithCaloriesOver(int calories);
	ArrayList<Meal> selectMealsWithCaloriesUnder(int calories);
	ArrayList<Meal> selectMealsWithProteinOver(int protein);
	void insertNewMeal(String title, String description, Collection<Ingredient> ingredients);
	void deleteMealById(long idme);
}
