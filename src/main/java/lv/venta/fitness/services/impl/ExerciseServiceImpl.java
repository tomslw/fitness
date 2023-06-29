package lv.venta.fitness.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.enums.Intensity;
import lv.venta.fitness.models.Exercise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.ExerciseRepo;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.repos.MuscleGroupsRepo;
import lv.venta.fitness.services.IExerciseService;
import lv.venta.fitness.services.IHealthDataService;

@Service
public class ExerciseServiceImpl implements IExerciseService{
	
	@Autowired
	private ExerciseRepo ExerciseRepo;
	
	@Autowired
	private MuscleGroupsRepo muscleRepo;
	
	@Autowired
	private HealthDataRepo healthRepo;

	@Override
	public ArrayList<Exercise> selectAllExercises() {
		return (ArrayList<Exercise>) ExerciseRepo.findAll();
	}
	
	@Override
	public ArrayList<Exercise> getExercisesByHealthId(long idhe) throws Exception {
		if(idhe < 1) {
			throw new Exception("Incorrect id");
		}
		else {
			HealthData healthData = healthRepo.findByIdhe(idhe);
			return (ArrayList<Exercise>) healthData.getWorkout();
		}
	}

	@Override
	public Exercise insertNewExercise(long idhe) throws Exception {
		if(idhe < 1) {
			throw new Exception("Incorrect id");
		}
		else {
			MuscleGroups muscleGroup = new MuscleGroups(Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, null);
			HealthData healthData = healthRepo.findByIdhe(idhe);
			Exercise Exercise = new Exercise("Exercise", "Description", 1, 1, muscleGroup, 1);
			Exercise.addHealthData(healthData);
			healthData.addExercise(Exercise);
			muscleGroup.setExercise(Exercise);
			
			ExerciseRepo.save(Exercise);
			muscleRepo.save(muscleGroup);
			healthRepo.save(healthData);

			return Exercise;
		}
	}

	@Override
	public void deleteExerciseById(long idex) throws Exception {
		if(idex > 0) {
			ExerciseRepo.deleteById(idex);
		}
		else {
			throw new Exception("Invalid muscle");
		}
		
	}

	@Override
	public void updateExerciseById(long idex, Exercise data) throws Exception {
		if(idex < 1) {
			throw new Exception("Invalid id!");
		}
		
		Exercise Exercise = ExerciseRepo.findById(idex).get();
		
		if(data.getTitle().length() < 3 || data.getTitle().length() > 30)
			throw new Exception("Invalid update data!");
		
		if(data.getDescription().length() < 3 || data.getTitle().length() > 100)
			throw new Exception("Invalid update data!");
		
		if(data.getRepetitions() < 1 || data.getRepetitions() > 100)
			throw new Exception("Invalid update data!");
		
		if(data.getRestInterval() < 0 || data.getRestInterval() > 10)
			throw new Exception("Invalid update data!");
		
		if(data.getAddedWeight() < 0 || data.getAddedWeight() > 1000)
			throw new Exception("Invalid update data!");
		
		Exercise.setTitle(data.getTitle());
		Exercise.setDescription(data.getDescription());
		Exercise.setRepetitions(data.getRepetitions());
		Exercise.setRestInterval(data.getRestInterval());
		Exercise.setAddedWeight(data.getAddedWeight());
		
		ExerciseRepo.save(Exercise);
	}



}
