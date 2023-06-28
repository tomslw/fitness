package lv.venta.fitness.services;

import lv.venta.fitness.models.MuscleGroups;

public interface IMuscleGroupsService {
	MuscleGroups getByExerciseId(long idex) throws Exception;
	MuscleGroups getByHealthDataId(long idhe) throws Exception;
	void editByExerciseId(long idex, MuscleGroups data) throws Exception;
	void editByHealthDataId(long idhe, MuscleGroups data) throws Exception;
}
