package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.User;

public interface IHealthDataService {
	ArrayList<HealthData> selectAllHealthData();
	ArrayList<HealthData> selectAllHealthDataByUser(User user);
	void deleteHealthDataById(long idhe);
}
