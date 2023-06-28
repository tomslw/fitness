package lv.venta.fitness.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.models.User;

public interface IHealthDataService {
	ArrayList<HealthData> selectAllHealthData();
	ArrayList<HealthData> selectAllHealthDataByUserId(long idus) throws Exception;
	void insertNewHealthData(float weight, float height, MuscleGroups muscleGroups, int caloriesSpent, LocalDate date) throws Exception;
	void deleteHealthDataById(long idhe) throws Exception;
	void deleteHealthDataByUserId(long idus) throws Exception;
	HealthData insertEmptyHealthDataEntry() throws Exception;
	HealthData selectHealthDataById(long id) throws Exception;
	void updateHealthDataById(long id, float weight, float height, int calories) throws Exception;
}
