package lv.venta.fitness.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.enums.Intensity;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.ExcersiseRepo;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.repos.MuscleGroupsRepo;
import lv.venta.fitness.services.IExcersiseService;
import lv.venta.fitness.services.IHealthDataService;

@Service
public class ExcersiseServiceImpl implements IExcersiseService{
	
	@Autowired
	private ExcersiseRepo excersiseRepo;
	
	@Autowired
	private MuscleGroupsRepo muscleRepo;
	
	@Autowired
	private HealthDataRepo healthRepo;

	@Override
	public ArrayList<Excersise> selectAllExcersises() {
		return (ArrayList<Excersise>) excersiseRepo.findAll();
	}

	/*
	@Override
	public ArrayList<Excersise> selectExcersisesByMuscle(String muscle) throws Exception {
		if (muscle != null) {
			ArrayList<Excersise> filteredResults = new ArrayList<>();
			ArrayList<Excersise> allExcersises = (ArrayList<Excersise>) excersiseRepo.findAll();
			for (Excersise excersise : allExcersises) {
				if(excersise.getTargetMuscles().contains(muscle)) {
					filteredResults.add(excersise);
				}
			}
			return filteredResults;
		}
		else {
			throw new Exception("Invalid muscle");
		}
	}
	*/

	@Override
	public Excersise insertNewExcersise(long idhe) {
		MuscleGroups muscleGroup = new MuscleGroups(Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, null);
		HealthData healthData = healthRepo.findByIdhe(idhe);
		Excersise excersise = new Excersise("Exercise", "Description", 1, 1, muscleGroup, 1);
		healthData.addExercise(excersise);
		excersiseRepo.save(excersise);
		muscleRepo.save(muscleGroup);
		healthRepo.save(healthData);

		return excersise;
	}

	@Override
	public void deleteExcersiseById(long idex) throws Exception {
		if(idex > 0) {
			excersiseRepo.deleteById(idex);
		}
		else {
			throw new Exception("Invalid muscle");
		}
		
	}

	@Override
	public void editExcersise(String title, String description, float restInterval, int repetitions,
			MuscleGroups targetMuscles, float addedWeight) {
		// TODO Auto-generated method stub
		
	}


}
