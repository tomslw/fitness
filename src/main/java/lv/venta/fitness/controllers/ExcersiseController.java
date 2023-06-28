package lv.venta.fitness.controllers;


import jakarta.validation.Valid;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.services.IExcersiseService;

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
public class ExcersiseController {

    @Autowired
    private IExcersiseService excersiseService;

    @Autowired
    private IHealthDataService healthDataService;

    @GetMapping("/showAll")
    Collection<Excersise> getAllExercises(Model model) {
        return excersiseService.selectAllExcersises();
    }

    /*
    @GetMapping("/showExerciseByMuscle/{muscle}")
    Collection<Excersise> getExerciseByMuscle(@PathVariable(name = "muscle") String muscle, Model model) throws Exception {
        return excersiseService.selectExcersisesByMuscle(muscle);
    }
    */

    @GetMapping("/delete/{id}")
    Collection<Excersise> getDeleteExercise(@PathVariable(name = "id") long id, Model model) throws Exception {
        excersiseService.deleteExcersiseById(id);
        return excersiseService.selectAllExcersises();
    }

    @GetMapping("/insertNewExercise")
    Excersise getAddExercise(long idhe) {
        try {
			return excersiseService.insertNewExcersise(idhe);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    @PostMapping("/insertNewExercise")
    void postAddExercise(@Valid @ModelAttribute("exercise") long idhe) {
        //excersiseService.insertNewExcersise(exercise.getTitle(), exercise.getDescription(), exercise.getRestInterval(), exercise.getRepetitions(), exercise.getTargetMuscles(), exercise.getAddedWeight());
        excersiseService.insertNewExcersise(idhe);
    }
}
