package lv.venta.fitness.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.enums.Intensity;
import lv.venta.fitness.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.repos.MealRepo;
import lv.venta.fitness.services.IMealService;

@Service
public class MealServiceImpl implements IMealService{
	
	@Autowired
	private MealRepo mealRepo;

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
	public void insertNewMeal(String title, String description, Collection<Ingredient> ingredients) throws Exception {
		if(title != null && description != null && ingredients != null) {
			Meal meal = new Meal(title, description, ingredients);
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
	public Meal insertEmptyMealEntry() throws Exception{
		Meal latestEntry = mealRepo.findTopByOrderByDate();

		if (latestEntry.getDate().toLocalDate().equals(LocalDate.now()))
			throw new Exception("Theres already and entry for today");


		Meal newEntry = new Meal(latestEntry.getTitle(), latestEntry.getDescription(), latestEntry.getIngredients());
		mealRepo.save(newEntry);
		return newEntry;
	}

}
