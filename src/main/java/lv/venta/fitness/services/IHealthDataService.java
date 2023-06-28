package lv.venta.fitness.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.Ingredient;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.models.User;

public interface IHealthDataService {
	ArrayList<HealthData> selectAllHealthData();
	ArrayList<HealthData> selectAllHealthDataByUserId(long idus) throws Exception;
	void deleteHealthDataById(long idhe) throws Exception;
	void deleteHealthDataByUserId(long idus) throws Exception;
}
