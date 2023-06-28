package lv.venta.fitness.controllers;

import jakarta.validation.Valid;
import lv.venta.fitness.controllers.HealthDataController.HealthDataListItem;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.Meal;
import lv.venta.fitness.services.IHealthDataService;
import lv.venta.fitness.services.IMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/meal")
public class MealController
{

    @Autowired
    private IMealService mealService;

//    @GetMapping("/showAll")
//    Collection<Meal> getAllMeals(Model model) {
//        return mealService.selectAllMeals();
//    }
//
//    @GetMapping("/showMealsWithCaloriesOver/{calories}")
//    Collection<Meal> getMealsWithCaloriesOver(@PathVariable(name = "calories") int calories, Model model) throws Exception {
//        return mealService.selectMealsWithCaloriesOver(calories);
//    }
//
//    @GetMapping("/showMealsWithCaloriesUnder/{calories}")
//    Collection<Meal> getMealsWithCaloriesUnder(@PathVariable(name = "calories") int calories, Model model) throws Exception {
//        return mealService.selectMealsWithCaloriesUnder(calories);
//    }
//
//    @GetMapping("/showMealsWithProteinOver/{protein}")
//    Collection<Meal> getMealsWithProteinOver(@PathVariable(name = "protein") int protein, Model model) throws Exception {
//        return mealService.selectMealsWithProteinOver(protein);
//    }
//
//    @GetMapping("/insertNewMeal")
//    Collection<Meal> getAddMeal(Model model){
//        return (Collection<Meal>) new Meal();
//    }
//
//    @PostMapping("/insertNewMeal")
//    void postAddMeal(@Valid @ModelAttribute("meal") Meal meal, BindingResult result) throws Exception {
//        mealService.insertNewMeal(meal.getTitle(), meal.getDescription(), meal.getCalories(), meal.getFat(), meal.getCarbohydrates(),  meal.getProtein());
//    }
    
    @GetMapping("/showAll/{id}")
    Collection<Meal> getAllMealsByHealthDataId(@PathVariable(name="id") long id){
        try {
			return mealService.getMealsByHealthId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Meal>();
		}
    }
    
    @GetMapping("/getFresh/{id}")
    Meal getFreshMealEntryById(@PathVariable(name="id") long id) {
    	System.out.println(id);
    	try {
    		Meal freshOne = mealService.insertEmptyMealEntry(id);
			return freshOne;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @PostMapping("/update/{id}")
    public long updateMealEntry(@PathVariable(name="id") long id, @RequestBody Meal data, BindingResult result) {
    	try {
    		mealService.updateMealById(id, data);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
			// should probably respond with some sort of response class telling the front end what's up
		}
    }

    @GetMapping("/delete/{idme}")
    void getDeleteMeal(@PathVariable(name = "idme") long idme, Model model) throws Exception {
        mealService.deleteMealById(idme);
    }

}
