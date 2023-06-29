package lv.venta.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.services.IMuscleGroupsService;

@RestController
@RequestMapping("/muscle")
public class MuscleGroupsController {
	
	@Autowired
	private IMuscleGroupsService muscleService;

	@GetMapping("/getByExercise/{id}")
    MuscleGroups getByExercise(@PathVariable(name="id") long id){
        try {
			return muscleService.getByExerciseId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	@GetMapping("/getByHealthData/{id}")
    MuscleGroups getByHealthData(@PathVariable(name="id") long id){
        try {
			return muscleService.getByHealthDataId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	@PostMapping("/updateByExercise/{id}")
    public void updateMusclesByExercise(@PathVariable(name="id") long id, @RequestBody MuscleGroups data, BindingResult result) {
    	try {
			muscleService.editByExerciseId(id, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@PostMapping("/updateByHealthData/{id}")
    public void updateMusclesByHealthData(@PathVariable(name="id") long id, @RequestBody MuscleGroups data, BindingResult result) {
		//System.out.println(data);
    	try {
			muscleService.editByHealthDataId(id, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
