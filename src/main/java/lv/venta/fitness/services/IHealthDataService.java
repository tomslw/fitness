package lv.venta.fitness.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.models.*;

public interface IHealthDataService {
	ArrayList<HealthData> selectAllHealthData();
	ArrayList<HealthData> selectAllHealthDataByUserId(long idus) throws Exception;
	void insertNewHealthData(float weight, float height, MuscleGroups muscleGroups, int caloriesSpent, LocalDate date) throws Exception;
	void deleteHealthDataById(long idhe) throws Exception;
	void deleteHealthDataByUserId(long idus) throws Exception;
	HealthData getHealthServiceById(long idhe);
	void addExercise(long idhe, Excersise excersise);
	void addMeal(long idhe, Meal meal);


}
