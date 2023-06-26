package lv.venta.fitness.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.models.Ingredient;
import lv.venta.fitness.repos.IngredientRepo;
import lv.venta.fitness.services.IIngredientService;

@Service
public class IngredientServiceImpl implements IIngredientService{
	
	@Autowired
	private IngredientRepo ingredientRepo;

	@Override
	public ArrayList<Ingredient> selectAllIngredients() {
		return (ArrayList<Ingredient>) ingredientRepo.findAll();
	}

	@Override
	public void insertNewIngredient(String title, String description, int calories, int fat, int carbohydrates,
			int protein) throws Exception {
		
		if(title != null && description != null && calories >= 0 && fat >= 0 && carbohydrates >= 0 && protein >= 0) {
			Ingredient ingredient = new Ingredient(title, description, calories, fat, carbohydrates, protein);
			ingredientRepo.save(ingredient);
		}
		else {
			throw new Exception("Invalid arguments");
		}
		
	}

	@Override
	public void deleteIngredientById(long idin) throws Exception {
		if(idin > 0) {
			ingredientRepo.deleteById(idin);
		}
		else {
			throw new Exception("Invalid id");
		}
	}

}
