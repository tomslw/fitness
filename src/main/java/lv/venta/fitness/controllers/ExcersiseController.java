package lv.venta.fitness.controllers;


import jakarta.validation.Valid;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.services.IExcersiseService;

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

    @GetMapping("/showAll")
    Collection<Excersise> getAllExercises(Model model) {
        return excersiseService.selectAllExcersises();
    }

    @GetMapping("/showExerciseByMuscle/{muscle}")
    Collection<Excersise> getExerciseByMuscle(@PathVariable(name = "muscle") String muscle, Model model) throws Exception {
        return excersiseService.selectExcersisesByMuscle(muscle);
    }

    @GetMapping("/delete/{id}")
    Collection<Excersise> getDeleteExercise(@PathVariable(name = "id") long id, Model model) throws Exception {
        excersiseService.deleteExcersiseById(id);
        return excersiseService.selectAllExcersises();
    }

    @GetMapping("/insertNewExercise")
    Collection<Excersise> getAddExercise(Model model) {
        return (Collection<Excersise>) new Excersise();
    }

    @PostMapping("/insertNewExercise")
    void postAddExercise(@Valid @ModelAttribute("exercise") Excersise exercise) {
        excersiseService.insertNewExcersise(exercise.getTitle(), exercise.getDescription(), exercise.getRestInterval(), exercise.getRepetitions(), exercise.getTargetMuscles(), exercise.getAddedWeight());
    }
}