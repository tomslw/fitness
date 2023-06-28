//package lv.venta.fitness.controllers;
//
//
//import jakarta.validation.Valid;
//import lv.venta.fitness.models.Excersise;
//import lv.venta.fitness.models.Ingredient;
//import lv.venta.fitness.services.IIngredientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//
//@RestController
//@RequestMapping("/ingredient")
//public class IngredientController
//{
//
//    @Autowired
//    private IIngredientService ingredientService;
//
//    @GetMapping("/showAll")
//    Collection<Ingredient> getAllIngredients(Model model){
//        return ingredientService.selectAllIngredients();
//    }
//
//    @GetMapping("/insertNewIngredient")
//    Collection<Ingredient> getAddIngredient(Model model){
//        return (Collection<Ingredient>) new Ingredient();
//    }
//
//    @PostMapping("/insertNewIngrient")
//    void postAddIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult result) throws Exception {
//        ingredientService.insertNewIngredient(ingredient.getTitle(), ingredient.getDescription(), ingredient.getCalories(), ingredient.getFat(), ingredient.getCarbohydrates(), ingredient.getProtein());
//    }
//
//    @GetMapping("/delete/{id}")
//    void getDeleteIngredient(@PathVariable(name = "idin") long idin, Model model) throws Exception {
//            ingredientService.deleteIngredientById(idin);
//            ingredientService.selectAllIngredients();
//    }
//
//}
