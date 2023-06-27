package lv.venta.fitness.controllers;

import jakarta.validation.Valid;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.Meal;
import lv.venta.fitness.services.IMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
public class MealController
{

    @Autowired
    private IMealService mealService;

    @GetMapping("/error")
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }

    @GetMapping("/showAll")
    public String getAllMeals(Model model) {
        model.addAttribute("meal", mealService.selectAllMeals());
        return "all-meal-page";
    }

    @GetMapping("/showMealsWithCaloriesOver/{calories}")
    public String getMealsWithCaloriesOver(@PathVariable(name = "calories") int calories, Model model){
        try {
            model.addAttribute("meals", mealService.selectMealsWithCaloriesOver(calories));
            return "all-meal-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/showMealsWithCaloriesUnder/{calories}")
    public String getMealsWithCaloriesUnder(@PathVariable(name = "calories") int calories, Model model){
        try {
            model.addAttribute("meals", mealService.selectMealsWithCaloriesUnder(calories));
            return "all-meal-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/showMealsWithProteinOver/{protein}")
    public String getMealsWithProteinOver(@PathVariable(name = "protein") int protein, Model model){
        try {
            model.addAttribute("meals", mealService.selectMealsWithProteinOver(protein));
            return "all-meal-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/insertNewMeal")
    public String getAddMeal(Model model){
        model.addAttribute("meal", new Meal());
        return "add-meal-page";
    }

    @PostMapping("/insertNewMeal")
    public String postAddMeal(@Valid @ModelAttribute("meal") Meal meal, BindingResult result) {
        if(!result.hasErrors()) {
            try {
                mealService.insertNewMeal(meal.getTitle(), meal.getDescription(), meal.getIngredients());
                return "redirect:/meal/showAll";
            }
            catch (Exception e) {
//                e.printStackTrace();
                return "redirect:/error";
            }
        } else {
            return "add-meal-page";
        }
    }

    @GetMapping("/delete/{idme}")
    public String getDeleteMeal(@PathVariable(name = "idme") long idme, Model model){
        try{
            mealService.deleteMealById(idme);
            model.addAttribute("exercises", mealService.selectAllMeals());
            return "all-meal-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

}
