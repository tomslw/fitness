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

//	@Override
//	public void insertNewMeal(String title, String description, int calories, int fat, int carbohydrates, int protein) throws Exception {
//		if(title != null && description != null) {
//			Meal meal = new Meal();
//			mealRepo.save(meal);
//		}
//		else {
//			throw new Exception("Invalid arguments");
//		}
//		
//	}

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
		HealthData healthData = healthRepo.findById(idhe).get();
		Meal meal = new Meal("title", "description", 1, 1, 1 ,1, healthData);

		healthData.addMeal(meal);
		mealRepo.save(meal);
		healthRepo.save(healthData);

		return meal;
	}

	@Override
	public ArrayList<Meal> getMealsByHealthId(long idhe) throws Exception {
		if (idhe < 1)
			throw new Exception("Invalid id!");
		
		HealthData healthData = healthRepo.findById(idhe).get();
		return (ArrayList<Meal>) healthData.getDiet();
	}


	@Override
	public void updateMealById(long idme, Meal data) throws Exception {
		if(idme < 1) {
			throw new Exception("Invalid id!");
		}

		Meal meal = mealRepo.findById(idme).get();
		
		if (data.getTitle().length() < 3 || data.getTitle().length() > 20)
			throw new Exception("Invalid update data");
		
		if (data.getDescription().length() < 3 || data.getDescription().length() > 20)
			throw new Exception("Invalid update data");
		
		if (data.getCalories() < 0 || data.getCalories() > 100000)
			throw new Exception("Invalid update data");
		
		if (data.getFat() < 0 || data.getFat() > 100000)
			throw new Exception("Invalid update data");
		
		if (data.getCarbohydrates() < 0 || data.getCarbohydrates() > 100000)
			throw new Exception("Invalid update data");
		
		if (data.getProtein() < 0 || data.getProtein() > 100000)
			throw new Exception("Invalid update data");
		
		
		meal.setTitle(data.getTitle());
		meal.setDescription(data.getDescription());
		meal.setCalories(data.getCalories());
		meal.setFat(data.getFat());
		meal.setCarbohydrates(data.getCarbohydrates());
		meal.setProtein(data.getProtein());


		mealRepo.save(meal);
	}
}
