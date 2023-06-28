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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.rmi.server.ExportException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/healthData")
public class HealthDataController {

    @Autowired
    private IHealthDataService healthDataService;

    @GetMapping("/showAll")
    List<HealthData> getAllHealthData(Model model){
        return healthDataService.selectAllHealthData();
    }
    
    @GetMapping("/getFresh")
    HealthData getFreshHealthEntry() {
    	try {
			return healthDataService.insertEmptyHealthDataEntry();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @PostMapping("/update/{id}")
    public void updateHealthEntry(@PathVariable(name="id") long id, @Valid HealthData data, BindingResult result) {
    	try {
			healthDataService.updateHealthDataById(id, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
