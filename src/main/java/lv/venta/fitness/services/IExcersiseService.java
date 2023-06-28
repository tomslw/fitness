package lv.venta.fitness.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;

public interface IExcersiseService {
	ArrayList<Excersise> selectAllExcersises();
	ArrayList<Excersise> selectExcersisesByMuscle(String muscle) throws Exception;
	void insertNewExcersise(String title, String description, float restInterval, int repetitions, ArrayList<String> targetMuscles, float addedWeight);
	void deleteExcersiseById(long idex) throws Exception;
}
