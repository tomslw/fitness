package lv.venta.fitness.controllers;


import jakarta.validation.Valid;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.Ingredient;
import lv.venta.fitness.services.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController
{

    @Autowired
    private IIngredientService ingredientService;

    @GetMapping("/error")
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }

    @GetMapping("/showAll")
    public String getAllIngredients(Model model){
        model.addAttribute("ingredients", ingredientService.selectAllIngredients());
        return "all-ingredients-page";
    }

    @GetMapping("/insertNewIngredient")
    public String getAddIngredient(Model model){
        model.addAttribute("ingredient", new Ingredient());
        return "add-ingredient-page";
    }

    @PostMapping("/insertNewIngrient")
    public String postAddIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult result) {
        if(!result.hasErrors()) {
            try {
                ingredientService.insertNewIngredient(ingredient.getTitle(), ingredient.getDescription(), ingredient.getCalories(), ingredient.getFat(), ingredient.getCarbohydrates(), ingredient.getProtein());
                return "redirect:/ingredient/showAll";
            }
            catch (Exception e) {
//                e.printStackTrace();
                return "redirect:/error";
            }
        } else {
            return "add-ingredient-page";
        }
    }

    @GetMapping("/delete/{id}")
    public String getDeleteIngredient(@PathVariable(name = "idin") long idin, Model model){
        try{
            ingredientService.deleteIngredientById(idin);
            model.addAttribute("ingredient", ingredientService.selectAllIngredients());
            return "all-ingredients-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

}
