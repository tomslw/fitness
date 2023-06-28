package lv.venta.fitness.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.services.IHealthDataService;

@Service
public class HealthDataServiceImpl implements IHealthDataService{
	
	@Autowired
	private HealthDataRepo healthRepo;

	@Override
	public ArrayList<HealthData> selectAllHealthData() {
		return (ArrayList<HealthData>) healthRepo.findAll();
	}

	@Override
	public ArrayList<HealthData> selectAllHealthDataByUserId(long idus) throws Exception {
		
		if (idus > 0) {
			ArrayList<HealthData> filteredResults = healthRepo.findAllByUserIdus(idus);
			return filteredResults;
		}
		else {
			throw new Exception("Incorrect id");
		}
			
	}

	@Override
	public void deleteHealthDataById(long idhe) throws Exception {
		if (idhe > 0) {
			healthRepo.deleteById(idhe);
		}
		else {
			throw new Exception("Incorrect id");
		}
	}

	@Override
	public void deleteHealthDataByUserId(long idus) throws Exception {
		
		if (idus > 0) {
			healthRepo.deleteByUserIdus(idus);
		}
		else {
			throw new Exception("Incorrect id");
		}
		
	}

	

}
