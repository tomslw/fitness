package lv.venta.fitness.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.models.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.services.IHealthDataService;

@Service
public class HealthDataServiceImpl implements IHealthDataService{
	
	@Autowired
	private HealthDataRepo healthRepo;

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
	public void insertNewHealthData(float weight, float height, MuscleGroups muscleGroups, int caloriesSpent,
			LocalDate date) throws Exception {
		if(weight > 0 && height > 0 && muscleGroups != null && caloriesSpent > 0 && date != null) {
			ArrayList<HealthData> allHealthData = (ArrayList<HealthData>) healthRepo.findAll();
			for(HealthData healthData : allHealthData) {
				if(healthData.getDate().isEqual(date)) {
					throw new Exception("There already is health data for this day");
				}
			}
			
			HealthData healthData = new HealthData(weight, height, muscleGroups, caloriesSpent, date);
			healthRepo.save(healthData);
		}
		else {
			throw new Exception("Invalid arguments");
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
	public HealthData getHealthServiceById(long idhe) {
		HealthData healthData = healthRepo.findByIdhe(idhe);
		return healthData;
	}

	@Override
	public void addExercise(long idhe, Excersise excersise) {
		HealthData healthData = getHealthServiceById(idhe);
		healthData.addExercise(excersise);
		healthRepo.save(healthData);
	}

	@Override
	public void addMeal(long idhe, Meal meal) {
		HealthData healthData = getHealthServiceById(idhe);
		healthData.addMeal(meal);
		healthRepo.save(healthData);
	}


}
