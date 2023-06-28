package lv.venta.fitness.services;

import lv.venta.fitness.models.MuscleGroups;

public interface IMuscleGroupsService {
	MuscleGroups getByExerciseId(long idex);
	MuscleGroups getByHealthDataId(long idhe);
	void editByExerciseId(long idex);
	void editByHealthDataId(long idhe);
}
