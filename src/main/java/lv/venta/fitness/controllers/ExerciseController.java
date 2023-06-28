package lv.venta.fitness.controllers;


import jakarta.validation.Valid;
import lv.venta.fitness.models.Exercise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.Meal;
import lv.venta.fitness.services.IExerciseService;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.services.IHealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private IExerciseService ExerciseService;

    @Autowired
    private IHealthDataService healthDataService;

    @GetMapping("/showAll")
    Collection<Exercise> getAllExercises(Model model) {
        return ExerciseService.selectAllExercises();
    }

    /*
    @GetMapping("/showExerciseByMuscle/{muscle}")
    Collection<Exercise> getExerciseByMuscle(@PathVariable(name = "muscle") String muscle, Model model) throws Exception {
        return ExerciseService.selectExercisesByMuscle(muscle);
    }
    */
    
    @GetMapping("/showAll/{id}")
    Collection<Exercise> getAllExerciseByHealthDataId(@PathVariable(name="id") long id){
        try {
			return ExerciseService.getExercisesByHealthId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Exercise>();
		}
    }

    @GetMapping("/delete/{id}")
    void getDeleteExercise(@PathVariable(name = "id") long id, Model model) throws Exception {
        ExerciseService.deleteExerciseById(id);
    }

    @GetMapping("/insertNewExercise/{id}")
    Exercise getAddExercise(@PathVariable(name = "id") long id) {
        try {
			return ExerciseService.insertNewExercise(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @PostMapping("/update/{id}")
    public void updateExercise(@PathVariable(name="id") long id, @Valid Exercise data, BindingResult result) {
    	try {
			ExerciseService.updateExerciseById(id, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /*
    @PostMapping("/insertNewExercise")
    void postAddExercise(@Valid @ModelAttribute("exercise") long idhe) {
        //ExerciseService.insertNewExercise(exercise.getTitle(), exercise.getDescription(), exercise.getRestInterval(), exercise.getRepetitions(), exercise.getTargetMuscles(), exercise.getAddedWeight());
        ExerciseService.insertNewExercise(idhe);
    }
    */
}
