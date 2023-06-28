package lv.venta.fitness.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.fitness.models.Exercise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;

public interface IExerciseService {
	ArrayList<Exercise> selectAllExercises();
	ArrayList<Exercise> getExercisesByHealthId(long idhe) throws Exception;
	//ArrayList<Exercise> selectExercisesByMuscle(String muscle) throws Exception;
	Exercise insertNewExercise(long idhe) throws Exception;
	void deleteExerciseById(long idex) throws Exception;
	void updateExerciseById(long idex, Exercise data) throws Exception;
}
