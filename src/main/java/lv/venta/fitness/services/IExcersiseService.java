package lv.venta.fitness.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;

public interface IExcersiseService {
	ArrayList<Excersise> selectAllExcersises();
	ArrayList<Excersise> getExcersisesByHealthId(long idhe);
	//ArrayList<Excersise> selectExcersisesByMuscle(String muscle) throws Exception;
	Excersise insertNewExcersise(long idhe);
	void deleteExcersiseById(long idex) throws Exception;
	void updateExcersiseById(long idex, Excersise data) throws Exception;
}
