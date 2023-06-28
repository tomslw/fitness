package lv.venta.fitness.controllers;

import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.services.IHealthDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/healthData")
public class HealthDataController {
	
	class HealthDataListItem {
		long idhe;
		LocalDate date;
		
		public long getIdhe() {
			return idhe;
		}

		public void setIdhe(long idhe) {
			this.idhe = idhe;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		HealthDataListItem(long idhe, LocalDate date) {
			this.idhe = idhe;
			this.date = date;
		}
	}

    @Autowired
    private IHealthDataService healthDataService;

    @GetMapping("/showAll")
    Collection<HealthDataListItem> getAllHealthDataDates(){
    	ArrayList<HealthDataListItem> newDateList = new ArrayList<HealthDataListItem>();
    	
    	for (HealthData item : healthDataService.selectAllHealthData()) {
    		newDateList.add(new HealthDataListItem(item.getIdhe(), item.getDate()));
    	}
        return newDateList;
    }
    
    @GetMapping("/entry/{id}")
    HealthData getHealthDataById(@PathVariable(name="id") long id){
        try {
			return healthDataService.selectHealthDataById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @GetMapping("/getFresh")
    HealthDataListItem getFreshHealthEntry() {
    	try {
    		HealthData freshOne = healthDataService.insertEmptyHealthDataEntry();
			return new HealthDataListItem(freshOne.getIdhe(), freshOne.getDate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @PostMapping("/update/{id}")
    public long updateHealthEntry(@PathVariable(name="id") long id, @RequestBody HealthData data, BindingResult result) {
    	try {
    		System.out.println(data.toString());
			healthDataService.updateHealthDataById(id, data.getWeight(), data.getHeight(), data.getCaloriesSpent());
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
			// should probably respond with some sort of response class telling the front end what's up
		}
    }

//    @GetMapping("/showHealthDataByUserId/{idus}")
//    Collection<HealthData> getHealthDataById(@PathVariable(name = "idus") long idus, Model model) throws Exception {
//        return healthDataService.selectAllHealthDataByUserId(idus);
//    }

    // havent implemented this in frontend
//    @GetMapping("/deleteById/{idhe}")
//    void getDeleteHealthData(@PathVariable(name = "idhe") long idhe, Model model) throws Exception {
//        healthDataService.deleteHealthDataById(idhe);
//    }

//    @GetMapping("/deleteByUserId/{idus}")
//    void getDeleteExerciseByUserID(@PathVariable(name = "idus") long idus, Model model) throws Exception {
//            healthDataService.deleteHealthDataByUserId(idus);
//    }


}
