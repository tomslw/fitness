package lv.venta.fitness.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.models.Ingredient;
import lv.venta.fitness.models.Meal;

public interface IMealService {
	ArrayList<Meal> selectAllMeals();
	ArrayList<Meal> selectMealsWithCaloriesOver(int calories) throws Exception;
	ArrayList<Meal> selectMealsWithCaloriesUnder(int calories) throws Exception;
	ArrayList<Meal> selectMealsWithProteinOver(int protein) throws Exception;
	void insertNewMeal(String title, String description, Collection<Ingredient> ingredients) throws Exception;
	void deleteMealById(long idme) throws Exception;
}
