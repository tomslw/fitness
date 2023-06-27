package lv.venta.fitness.controllers;


import jakarta.validation.Valid;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.services.IExcersiseService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise")
public class ExcersiseController {

	@Autowired
    private IExcersiseService excersiseService;
	
    @GetMapping("/showAll")
    Collection<Excersise> getAllExercises(Model model){
        return excersiseService.selectAllExcersises();
    }

    @GetMapping("/exercise/showExerciseByMuscle/{muscle}")
    public String getExerciseByMuscle(@PathVariable(name = "muscle") String muscle, Model model){
        try {
            model.addAttribute("exercises", excersiseService.selectExcersisesByMuscle(muscle));
            return "all-exercises-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/exercise/delete/{id}")
    public String getDeleteExercise(@PathVariable(name = "id") long id, Model model){
        try{
            excersiseService.deleteExcersiseById(id);
            model.addAttribute("exercises", excersiseService.selectAllExcersises());
            return "all-exercises-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/exercise/insertNewExercise")
    public String getAddExercise(Model model){
        model.addAttribute("exercise", new Excersise());
        return "add-exercise-page";
    }

    @PostMapping("/exercise/insertNewExercise")
    public String postAddExercise(@Valid @ModelAttribute("exercise") Excersise exercise, BindingResult result) {
        if(!result.hasErrors()) {
            try {
                excersiseService.insertNewExcersise(exercise.getTitle(), exercise.getDescription(), exercise.getRestInterval(), exercise.getRepetitions(), exercise.getTargetMuscles(), exercise.getAddedWeight());
                return "redirect:/exercise/showAll";
            }
            catch (Exception e) {
                e.printStackTrace();
                return "error-page";
            }
        } else {
        	return "add-exercise-page";
        }
    }

}
