package lv.venta.fitness.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;

public interface IExcersiseService {
	ArrayList<Excersise> selectAllExcersises();
	//ArrayList<Excersise> selectExcersisesByMuscle(String muscle) throws Exception;
	Excersise insertNewExcersise(long idhe);
	void editExcersise(String title, String description, float restInterval, int repetitions, MuscleGroups targetMuscles, float addedWeight);
	void deleteExcersiseById(long idex) throws Exception;
}
