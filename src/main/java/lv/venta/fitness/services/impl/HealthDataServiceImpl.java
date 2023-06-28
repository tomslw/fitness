package lv.venta.fitness.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.enums.Intensity;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.Meal;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.repos.MuscleGroupsRepo;
import lv.venta.fitness.services.IHealthDataService;

@Service
public class HealthDataServiceImpl implements IHealthDataService{
	
	@Autowired
	private HealthDataRepo healthRepo;
	
	@Autowired
	private MuscleGroupsRepo muscleRepo;

	@Override
	public ArrayList<HealthData> selectAllHealthData() {
		return (ArrayList<HealthData>) healthRepo.findAll();
	}

	@Override
	public ArrayList<HealthData> selectAllHealthDataByUserId(long idus) throws Exception {
		
		if (idus > 0) {
			ArrayList<HealthData> filteredResults = healthRepo.findAllByUserIdus(idus);
			return filteredResults;
		}
		else {
			throw new Exception("Incorrect id");
		}
			
	}

	@Override
	public void deleteHealthDataById(long idhe) throws Exception {
		if (idhe > 0) {
			healthRepo.deleteById(idhe);
		}
		else {
			throw new Exception("Incorrect id");
		}
	}

	@Override
	public void deleteHealthDataByUserId(long idus) throws Exception {
		
		if (idus > 0) {
			healthRepo.deleteByUserIdus(idus);
		}
		else {
			throw new Exception("Incorrect id");
		}
		
	}

	@Override
	public HealthData insertEmptyHealthDataEntry() throws Exception{
		MuscleGroups muscleGroup = new MuscleGroups(Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, null);
		
		HealthData latestEntry = healthRepo.findTopByOrderByDate();
		
		if (latestEntry.getDate().toLocalDate().equals(LocalDate.now()))
			throw new Exception("Theres already and entry for today");
		
											// take weight and height from previous entry
		HealthData newEntry = new HealthData(latestEntry.getWeight(), latestEntry.getHeight(), muscleGroup, new ArrayList<Meal>(), new ArrayList<Excersise>(), 0, LocalDateTime.now(), null);
		healthRepo.save(newEntry);
		muscleRepo.save(muscleGroup);
		return newEntry;
	}

	@Override
	public void updateHealthDataById(long id, HealthData data) throws Exception {
		if (id < 1)
			throw new Exception("Invalid id!");
		
		HealthData target = healthRepo.findById(id).get();
		
		target.setCaloriesSpent(data.getCaloriesSpent());
//		target.setDiet(data.getDiet()); this should be done in the meal service
		// same with muscleGroups
		// same with Exercises
		target.setHeight(data.getHeight());
		target.setWeight(data.getWeight());
		
		healthRepo.save(target);
	}

}
