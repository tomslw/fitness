package lv.venta.fitness.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.repos.ExcersiseRepo;
import lv.venta.fitness.services.IExcersiseService;

public class ExcersiseServiceImpl implements IExcersiseService{
	
	@Autowired
	private ExcersiseRepo excersiseRepo;

	@Override
	public ArrayList<Excersise> selectAllExcersises() {
		return (ArrayList<Excersise>) excersiseRepo.findAll();
	}

	@Override
	public ArrayList<Excersise> selectExcersisesByMuscle(String muscle) throws Exception {
		if (muscle != null) {
			ArrayList<Excersise> filteredResults = new ArrayList<>();
			ArrayList<Excersise> allExcersises = (ArrayList<Excersise>) excersiseRepo.findAll();
			for (Excersise excersise : allExcersises) {
				if(excersise.getTargetMuscles().contains(muscle)) {
					filteredResults.add(excersise);
				}
			}
			return filteredResults;
		}
		else {
			throw new Exception("Invalid muscle");
		}
	}

	@Override
	public void insertNewExcersise(String title, String description, float restInterval, int repetitions,
			ArrayList<String> targetMuscles, float addedWeight) {
		Excersise excersise = new Excersise(title, description, restInterval, repetitions, targetMuscles, addedWeight);
		excersiseRepo.save(excersise);
		
	}

	@Override
	public void deleteExcersiseById(long idex) throws Exception {
		if(idex > 0) {
			excersiseRepo.deleteById(idex);
		}
		else {
			throw new Exception("Invalid muscle");
		}
		
	}

}
