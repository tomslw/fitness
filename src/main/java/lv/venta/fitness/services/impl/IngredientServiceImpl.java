package lv.venta.fitness.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.fitness.models.Ingredient;
import lv.venta.fitness.repos.IngredientRepo;
import lv.venta.fitness.services.IIngredientService;

public class IngredientServiceImpl implements IIngredientService{
	
	@Autowired
	private IngredientRepo ingredientRepo;

	@Override
	public ArrayList<Ingredient> selectAllIngredients() {
		return (ArrayList<Ingredient>) ingredientRepo.findAll();
	}

	@Override
	public void insertNewIngredient(String title, String description, int calories, int fat, int carbohydrates,
			int protein) {
		
		Ingredient ingredient = new Ingredient(title, description, calories, fat, carbohydrates, protein);
		
		
	}

	@Override
	public void deleteIngredientById(long idin) {
		// TODO Auto-generated method stub
		
	}

}
