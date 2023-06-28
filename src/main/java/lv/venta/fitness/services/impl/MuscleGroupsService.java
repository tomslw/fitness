package lv.venta.fitness.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.ExcersiseRepo;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.repos.MuscleGroupsRepo;
import lv.venta.fitness.services.IMuscleGroupsService;

@Service
public class MuscleGroupsService implements IMuscleGroupsService{

	@Autowired
	private ExcersiseRepo excersiseRepo;
	
	@Autowired
	private HealthDataRepo healthRepo;
	
	@Autowired
	private MuscleGroupsRepo muscleRepo;
	
	@Override
	public MuscleGroups getByExerciseId(long idex) throws Exception {
		if(idex < 1) {
			throw new Exception("Incorrect id");
		}
		else {
			return excersiseRepo.findById(idex).get().getTargetMuscles();
		}
	}

	@Override
	public MuscleGroups getByHealthDataId(long idhe) throws Exception {
		if(idhe < 1) {
			throw new Exception("Incorrect id");
		}
		else {
			return healthRepo.findById(idhe).get().getMuscleGroups();
		}
	}

	@Override
	public void editByExerciseId(long idex, MuscleGroups data) throws Exception {
		if(idex < 1) {
			throw new Exception("Invalid id!");
		}
		
		Excersise exercise = excersiseRepo.findById(idex).get();
		MuscleGroups muscleGroups = exercise.getTargetMuscles();
		
		muscleGroups.setChest(data.getChest());
		muscleGroups.setBack(data.getBack());
		muscleGroups.setBiceps(data.getBiceps());
		muscleGroups.setTriceps(data.getTriceps());
		muscleGroups.setForearms(data.getForearms());
		muscleGroups.setAbdomen(data.getAbdomen());
		muscleGroups.setCalves(data.getCalves());
		muscleGroups.setHamstrings(data.getHamstrings());
		muscleGroups.setQuadriceps(data.getQuadriceps());
		muscleGroups.setGluteus(data.getGluteus());
		muscleGroups.setTrapezius(data.getTrapezius());
		muscleGroups.setDeltoid(data.getDeltoid());
		
		
		muscleRepo.save(muscleGroups);
		exercise.setTargetMuscles(muscleGroups);
		excersiseRepo.save(exercise);
		
	}

	@Override
	public void editByHealthDataId(long idhe, MuscleGroups data) throws Exception {
		if(idhe < 1) {
			throw new Exception("Invalid id!");
		}
		
		HealthData healthData = healthRepo.findById(idhe).get();
		MuscleGroups muscleGroups = healthData.getMuscleGroups();
		
		muscleGroups.setChest(data.getChest());
		muscleGroups.setBack(data.getBack());
		muscleGroups.setBiceps(data.getBiceps());
		muscleGroups.setTriceps(data.getTriceps());
		muscleGroups.setForearms(data.getForearms());
		muscleGroups.setAbdomen(data.getAbdomen());
		muscleGroups.setCalves(data.getCalves());
		muscleGroups.setHamstrings(data.getHamstrings());
		muscleGroups.setQuadriceps(data.getQuadriceps());
		muscleGroups.setGluteus(data.getGluteus());
		muscleGroups.setTrapezius(data.getTrapezius());
		muscleGroups.setDeltoid(data.getDeltoid());
		
		
		muscleRepo.save(muscleGroups);
		healthData.setMuscleGroups(muscleGroups);
		healthRepo.save(healthData);
		
	}

}
