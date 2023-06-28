package lv.venta.fitness.services.impl;

import java.util.ArrayList;

import lv.venta.fitness.models.*;
import lv.venta.fitness.repos.HealthDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.repos.MealRepo;
import lv.venta.fitness.services.IMealService;

@Service
public class MealServiceImpl implements IMealService{
	
	@Autowired
	private MealRepo mealRepo;

	@Autowired
	private HealthDataRepo healthRepo;

	@Override
	public ArrayList<Meal> selectAllMeals() {
		return (ArrayList<Meal>) mealRepo.findAll();
	}

	@Override
	public ArrayList<Meal> selectMealsWithCaloriesOver(int calories) throws Exception {
		if(calories > 0 && calories < 10000) {
			ArrayList<Meal> allMeals = selectAllMeals();
			ArrayList<Meal> filteredResults = new ArrayList<>();
			for (Meal meal : allMeals) {
				if(meal.getCalories() > calories) {
					filteredResults.add(meal);
				}
			}
			return filteredResults;
		}
		else {
			throw new Exception("Invalid input");
		}
		
	}

	@Override
	public ArrayList<Meal> selectMealsWithCaloriesUnder(int calories) throws Exception {
		if(calories > 0 && calories < 10000) {
			ArrayList<Meal> allMeals = selectAllMeals();
			ArrayList<Meal> filteredResults = new ArrayList<>();
			for (Meal meal : allMeals) {
				if(meal.getCalories() < calories) {
					filteredResults.add(meal);
				}
			}
			return filteredResults;
		}
		else {
			throw new Exception("Invalid input");
		}
	}

	@Override
	public ArrayList<Meal> selectMealsWithProteinOver(int protein) throws Exception {
		if(protein >= 0 && protein < 10000) {
			ArrayList<Meal> allMeals = selectAllMeals();
			ArrayList<Meal> filteredResults = new ArrayList<>();
			for (Meal meal : allMeals) {
				if(meal.getProtein() > protein) {
					filteredResults.add(meal);
				}
			}
			return filteredResults;
		}
		else {
			throw new Exception("Invalid input");
		}
	}

	@Override
	public void insertNewMeal(String title, String description) throws Exception {
		if(title != null && description != null) {
			Meal meal = new Meal(title, description);
			mealRepo.save(meal);
		}
		else {
			throw new Exception("Invalid arguments");
		}
		
	}

	@Override
	public void deleteMealById(long idme) throws Exception {
		if(idme > 0) {
			mealRepo.deleteById(idme);
		}
		else {
			throw new Exception("Invalid id");
		}
	}

	@Override
	public Meal insertEmptyMealEntry(long idhe) {
		HealthData healthData = healthRepo.findbyIdhe(idhe);
		Meal meal = new Meal("title", "description");

		healthData.addMeal(meal);
		mealRepo.save(meal);
		healthRepo.save(healthData);

		return meal;
	}

}
