package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.User;

public interface IHealthDataService {
	ArrayList<HealthData> selectAllHealthData();
	ArrayList<HealthData> selectAllHealthDataByUserId(long idus) throws Exception;
	void deleteHealthDataById(long idhe) throws Exception;
	void deleteHealthDataByUserId(long idus) throws Exception;
}
