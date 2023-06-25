package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.Excersise;

public interface IExcersiseService {
	ArrayList<Excersise> selectAllExcersises();
	ArrayList<Excersise> selectExcersisesByMuscle(String muscle);
	void deleteExcersiseById(long idex);
}
