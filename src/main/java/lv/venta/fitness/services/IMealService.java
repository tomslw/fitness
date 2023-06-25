package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.Meal;

public interface IMealService {
	ArrayList<Meal> selectAllMeals();
	ArrayList<Meal> selectMealsWithCaloriesOver(int calories);
	ArrayList<Meal> selectMealsWithCaloriesUnder(int calories);
	ArrayList<Meal> selectMealsWithProteinOver(int protein);
	void deleteMealById(long idme);
}
